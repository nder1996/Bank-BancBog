package bdb.Backend.util;

import org.slf4j.Logger;

public class LogUtils {

    private static final String RESET = "\033[0m";
    private static final String BLUE_BOLD = "\033[1;34m";
    private static final String GREEN_BOLD = "\033[1;32m";
    private static final String RED_BOLD = "\033[1;31m";
    private static final String YELLOW_BOLD = "\033[1;33m";
    private static final String PURPLE_BOLD = "\033[1;35m";
    private static final String CYAN = "\033[0;36m";


    public static void inicioOperacion(Logger logger, String operacion, Object... params) {
        logger.info(BLUE_BOLD + "🔍 Iniciando " + operacion + RESET + CYAN + " - Params: {}" + RESET,
                params.length == 0 ? "ninguno" : params);
    }

    public static void finOperacion(Logger logger, String operacion, Object... params) {
        logger.info(GREEN_BOLD + "✅ Finalizado " + operacion + RESET + CYAN + " - Result: {}" + RESET,
                params.length == 0 ? "success" : params);
    }

    public static void error(Logger logger, String mensaje, Object... params) {
        logger.error(RED_BOLD + "❌ Error: " + mensaje + RESET + CYAN + " - Detalles: {}" + RESET,
                params.length == 0 ? "sin detalles" : params);
    }

    public static void advertencia(Logger logger, String mensaje, Object... params) {
        logger.warn(YELLOW_BOLD + "⚠️ Advertencia: " + mensaje + RESET + CYAN + " - Info: {}" + RESET,
                params.length == 0 ? "sin info adicional" : params);
    }

    public static void proceso(Logger logger, String mensaje, Object... params) {
        logger.info(PURPLE_BOLD + "⚙️ Proceso: " + mensaje + RESET + CYAN + " - Datos: {}" + RESET,
                params.length == 0 ? "sin datos" : params);
    }

}
