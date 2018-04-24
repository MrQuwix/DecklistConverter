import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class JavaHttpUrlConnectionReader {


    public JavaHttpUrlConnectionReader()
    {

    }

    /**
     * Returns the output from the given URL.
     *
     * I tried to hide some of the ugliness of the exception-handling
     * in this method, and just return a high level Exception from here.
     * Modify this behavior as desired.
     *
     * @param desiredUrl
     * @return
     * @throws Exception
     */
    public String getMultiverseIdFromName(String desiredUrl)
            throws Exception
    {
        URL url = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder;

        try
        {
            // create the HttpURLConnection
            url = new URL(desiredUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            // just want to do an HTTP GET here
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-GB;     rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13 (.NET CLR 3.5.30729)");

            // uncomment this if you want to write output to this url
            //connection.setDoOutput(true);

            // give it 15 seconds to respond
            connection.setReadTimeout(15*1000);
            connection.connect();

            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }

            JSONObject json = new JSONObject(stringBuilder.toString());

            JSONArray jsonArray = json.getJSONArray("cards");

            JSONObject original = jsonArray.getJSONObject(jsonArray.length()-1);

            String output = original.get("multiverseid").toString();


            return output;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        finally
        {
            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }
        }
    }
}

