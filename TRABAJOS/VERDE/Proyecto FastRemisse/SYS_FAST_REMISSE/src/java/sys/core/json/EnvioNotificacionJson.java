/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.json;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author admin
 */
@Controller
@RequestMapping(value = "envioNotificacionJson")
public class EnvioNotificacionJson implements Serializable {

    private static String API_KEY = "";

    public static boolean enviarMensajePrueba(String mensaje, List<String> registration_ids) throws IOException {
        //String GCM_URL = "https://android.googleapis.com/gcm/send";
        boolean estadoConsulta = true;
        API_KEY = "AIzaSyAYsCOr12ulRJAcCf8S0Nlf8Ss4RVHLDLM";
        Message.Builder msg = new Message.Builder();
        msg.addData("mensaje", mensaje);
        Sender sender = new Sender(API_KEY);
        if (!registration_ids.isEmpty()) {
            for (int i = 0; i < registration_ids.size(); i++) {
                MulticastResult result = sender.send(msg.build(), Arrays.asList(registration_ids.get(i).toString()), 3);
                if (result.getResults() != null) {
                    int canonicalRegId = result.getCanonicalIds();
                    if (canonicalRegId != 0) {
                    }
                } else {
                    int error = result.getFailure();
                    estadoConsulta = false;
                }
            }
        }
        return estadoConsulta;
    }

    public static String getAPI_KEY() {
        return API_KEY;
    }

    public static void setAPI_KEY(String API_KEY) {
        EnvioNotificacionJson.API_KEY = API_KEY;
    }
}
