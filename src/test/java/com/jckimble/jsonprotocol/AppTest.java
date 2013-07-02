package com.jckimble.jsonprotocol;

import com.jckimble.jsonprotocol.JSONCallback;
import com.jckimble.jsonprotocol.JSONServer;
import com.jckimble.jsonprotocol.JSONClient;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase implements JSONCallback
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testStart(){
        JSONServer tcp=new JSONServer(8000);
        tcp.setCallBack(this);
        tcp.start();
        JSONClient client=new JSONClient("localhost",8000);
        client.setCallBack(this);
        client.run();
        client.sendRequest("{\"action\":\"join\",\"key\":\"md5 hash\"}");
        long start=System.currentTimeMillis();
        long end=start+10000;
        while(end>System.currentTimeMillis()){
            continue;
        }
        client.close();
        tcp.close();
    }

    public void parseJSON(JSONObject jsonObj, JSONClient jsonClient) {
        try {
            JSONObject error=new JSONObject();
            error.put("action", "error");
            error.put("msg", "Couldn't Understand Request");
            jsonClient.sendRequest(error);
        } catch (JSONException ex) {
            JSONError(null,ex);
        }
    }

    public void parseJSON(JSONArray jsonArray, JSONClient jsonClient) {
        try {
            JSONObject error=new JSONObject();
            error.put("action", "error");
            error.put("msg", "Couldn't Understand Request");
            jsonClient.sendRequest(error);
        } catch (JSONException ex) {
            JSONError(null,ex);
        }
    }

    public void JSONError(String json, Exception reason) {
        System.out.println("-------------------------------------------------");
        if(reason != null){
            reason.printStackTrace();
        }
        if(json != null)
            System.out.println(json);
        System.out.println("-------------------------------------------------");
    }
}
