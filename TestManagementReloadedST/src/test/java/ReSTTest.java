import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class ReSTTest {

    private static Client client;
    private static WebTarget wt;
    private static ObjectMapper om;

    @BeforeClass
    public static void initClient(){
        client = ClientBuilder.newClient();
        wt = client.target("http://localhost:8080/tests/api");
        om = new ObjectMapper();
    }

    @Test
    public void getTest(){
        Response response = wt.path("tests").request(MediaType.APPLICATION_JSON).get();
        JsonArray payload = response.readEntity(JsonArray.class);
        assertThat(payload.size(),is(1));
    }

    @Test
    public void postAndDeleteTest() throws IOException {
        Pupil p = new Pupil("Erik","Himmel","if150113","4BHIF");
        System.out.println(om.writeValueAsString(p));
        Response resp = wt.path("pupils").request(MediaType.APPLICATION_JSON).post(Entity.json(om.writeValueAsString(p)));

        assertThat(resp.getStatus(), is(200));
        JsonObject inserted = resp.readEntity(JsonObject.class);
        assertThat(inserted, is(notNullValue()));
        Pupil insertedPupil = om.readValue(inserted.toString(),Pupil.class);
        assertThat(insertedPupil.getMatNr(),is(p.getMatNr()));

        resp = wt.path("pupils/"+insertedPupil.getId()).request().delete();
        assertThat(resp.getStatus(),is(204));
    }

    @Test
    public void putTest() throws IOException {
        Response response= wt.path("pupils").queryParam("id",1).request(MediaType.APPLICATION_JSON).get();
        JsonValue entity = response.readEntity(JsonValue.class);
        JsonObject payload = entity.asJsonArray().getJsonObject(0);

        Pupil p = om.readValue(payload.toString(),Pupil.class);

        p.setLastName("Buttersohn");
        System.out.println(om.writeValueAsString(p));

        Response resp = wt.path("pupils").request(MediaType.APPLICATION_JSON).put(Entity.json(om.writeValueAsString(p)));
        JsonObject inserted = resp.readEntity(JsonObject.class);
        assertThat(inserted, is(notNullValue()));
        Pupil insertedPupil = om.readValue(inserted.toString(),Pupil.class);
        System.out.println(om.writeValueAsString(insertedPupil));

        assertThat(insertedPupil.getMatNr(),is(p.getMatNr()));
        assertThat(insertedPupil.getLastName(),is(p.getLastName()));
    }
}
