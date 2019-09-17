package top.xuebiao.signature_check;

import org.apache.cordova.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * This class echoes a string called from JavaScript.
 */
public class CordovaPluginSignatureCheck extends CordovaPlugin {
    private Context context;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        context = this.cordova.getActivity().getApplicationContext(); 

        if (action.equals("checkSignature")) {
            this.checkSignature(callbackContext);
            return true;
        }
        return false;
    }

    private void checkSignature(CallbackContext callbackContext) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            Signature[] sig = pi.signatures;
            String signString = sig[0].toCharsString();
            String result = HmacSHA256(signString, "go-trade-app");

            String s = "ASDFASDFASDFASDFASDFASDFASDF";
            if(s.equalsIgnoreCase(result)){
                callbackContext.success("true");
            }else{
                callbackContext.success("false");
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            callbackContext.error("PackageManager NameNotFoundException");
        }
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
    private String HmacSHA256(String content, String salt){
        String result = "";
        try {
            String secret = salt;
            String message = content;

            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            byte[] temp = sha256_HMAC.doFinal(message.getBytes());
            result = bytesToHex(temp);
        }
        catch (Exception e){
            System.out.println("Error");
        }
        return result;
    }
}
