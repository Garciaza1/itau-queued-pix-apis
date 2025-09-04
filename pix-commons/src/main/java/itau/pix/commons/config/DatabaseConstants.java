package itau.pix.commons.config;

public class DatabaseConstants {

    private DatabaseConstants() {}

    public static final String DATABASE_NAME = "itau_challenge";
    public static final String PIX_COLLECTION = "chaves_pix";
    public static final String TRANSACTION_COLLECTION = "transacoes_pix";
    public static final String CONNECTION_STRING = "mongodb://localhost:27017/itau_challenge";
}
