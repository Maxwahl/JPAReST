import org.junit.Before;
import org.junit.Test;

import javax.json.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestManagementRestTests
{
    private Client client;
    private WebTarget tut;

    @Before
    public void initClient(){
        this.client = ClientBuilder.newClient();
        this.tut = client.target("http://localhost:8080/TestManagement/api/");
    }

    @Test
    public void getTest(){
        Response response= tut.path("students").queryParam("id",1).request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));


        JsonObjectBuilder exBuilder = Json.createObjectBuilder();
        JsonArrayBuilder tests = Json.createArrayBuilder();
        JsonObjectBuilder test = Json.createObjectBuilder();
        test.add("dateHeld","2019-01-21T19:56:02.883");
        test.add("grade",1);
        test.add("id",1);
        tests.add(test);
        exBuilder.add("id",1);
        exBuilder.add("name","Horst Lichter");
        exBuilder.add("tests",tests);
        JsonObject expected = exBuilder.build();
        JsonArray act = response.readEntity(JsonArray.class);

        assertThat(act.getJsonObject(0), equalTo(expected));
    }

    @Test
    public void postTest(){
        JsonObjectBuilder t = Json.createObjectBuilder();
        t.add("id", 2);
        t.add("name", "Horst Lichter");
        JsonObject obj = t.build();
        Response resp = this.tut.path("students").request(MediaType.APPLICATION_JSON).post(Entity.json(obj.toString()));
        assertThat(resp.getStatus(), is(200));
        JsonObject inserted = resp.readEntity(JsonObject.class);
        assertThat(inserted, is(notNullValue()));

        assertThat(inserted.getInt("id"),is(2));
        assertThat(inserted.getString("name"),is("Horst Lichter"));
    }
    @Test
    public void deleteTest(){
        Response response = tut.path("tests/1").request().delete();
        assertThat(response.getStatus(),is(204));
    }
    @Test
    public void putTest(){
        JsonObjectBuilder t = Json.createObjectBuilder();
        t.add("id", 1);
        t.add("name", "Horst Lichter");
        JsonObject obj = t.build();
        Response resp = this.tut.path("students").request(MediaType.APPLICATION_JSON).put(Entity.json(obj.toString()));
        assertThat(resp.getStatus(), is(200));
        JsonObject inserted = resp.readEntity(JsonObject.class);
        assertThat(inserted, is(notNullValue()));

        assertThat(inserted.getInt("id"),is(1));
        assertThat(inserted.getString("name"),is("Horst Lichter"));
    }
}
