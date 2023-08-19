package application.http.util;

public class HttpQueryUtil {
    public static String getParameterValue(String parameterKey, String query) {
        String[] split = query.split("&"); //key=value

        for (String s : split) {
            String[] split1 = s.split("=");

            if (split1[0].equals(parameterKey))
                return split1[1];
        }

        return null;
    }
}
