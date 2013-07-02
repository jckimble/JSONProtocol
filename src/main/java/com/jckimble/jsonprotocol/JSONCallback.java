/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jckimble.jsonprotocol;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author jckimble
 */
public interface JSONCallback {
    public void parseJSON(JSONObject jsonObj, JSONClient jsonClient);
    public void parseJSON(JSONArray jsonArray, JSONClient jsonClient);
    public void JSONError(String json,Exception reason);
}
