package za.ac.cput.utils;

public class DBUtils {

    // Common Database attributes
    public static final String AUTHENTICATED_STUDENT_EMAIL = "studentEmail";
    public static final String AUTHENTICATED_STUDENT_ID = "studentId";
    public static final String AUTHENTICATED_STUDENT_NAME = "studentName";
    public static final String DATABASE_NAME = "hungrystudents.db";
    public static final int DATABASE_VERSION = 8;

    // STUDENT TABLE
    public static final String STUDENT_TABLE = "student";

    public static final String COLUMN_STUDENT_ID = "id";
    public static final String COLUMN_STUDENT_FULL_NAME = "full_name";
    public static final String COLUMN_STUDENT_EMAIL_ADDRESS = "email_address";
    public static final String COLUMN_STUDENT_DATE_OF_BIRTH = "date_of_birth";

    public static final String COLUMN_STUDENT_CREATED_AT = "created_at";

    public static final String COLUMN_STUDENT_TOTAL_DONATED_POINTS= "donated_points";
    public static final String COLUMN_STUDENT_POINTS_BALANCE = "point_balance";
    public static final String COLUMN_STUDENT_PASSWORD = "password";


    public static final String CREATE_STUDENT_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + STUDENT_TABLE + "(" + COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_STUDENT_FULL_NAME + " TEXT,"  + COLUMN_STUDENT_EMAIL_ADDRESS + " TEXT," + COLUMN_STUDENT_DATE_OF_BIRTH + " TEXT,"
            + COLUMN_STUDENT_POINTS_BALANCE + " INTEGER," + COLUMN_STUDENT_TOTAL_DONATED_POINTS + " INTEGER, " + COLUMN_STUDENT_CREATED_AT + " TEXT," + COLUMN_STUDENT_PASSWORD + " TEXT)";

    public static final String DROP_STUDENT_TABLE_QUERY = "DROP TABLE IF EXISTS " + STUDENT_TABLE;



    // OBJECTIVE TABLE
    public static final String OBJECTIVE_TABLE = "objective";
    public static final String COLUMN_OBJECTIVE_ID = "objective_id";
    public static final String COLUMN_OBJECTIVE_TITLE = "title";
    public static final String COLUMN_OBJECTIVE_DESCRIPTION = "description";
    public static final String COLUMN_OBJECTIVE_POINTS = "points";

    public static final String CREATE_OBJECTIVE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + OBJECTIVE_TABLE + "(" + COLUMN_OBJECTIVE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_OBJECTIVE_TITLE + " TEXT,"  + COLUMN_OBJECTIVE_DESCRIPTION + " TEXT," + COLUMN_OBJECTIVE_POINTS + " INTEGER)";

    public static final String DROP_OBJECTIVE_TABLE_QUERY = "DROP TABLE IF EXISTS " + OBJECTIVE_TABLE;





    // STUDENT OBJECTIVE TABLE
    public static final String STUDENT_OBJECTIVE_TABLE = "student_objective";
    public static final String COLUMN_STUDENT_OBJECTIVE_STUDENT_ID = "student_id";
    public static final String COLUMN_STUDENT_OBJECTIVE_OBJECTIVE_ID = "objective_id";
    public static final String COLUMN_STUDENT_OBJECTIVE_TITLE = "objective_title";

    public static final String COLUMN_STUDENT_OBJECTIVE_DATE_ACHIEVED = "date_achieved";


    public static final String CREATE_STUDENT_OBJECTIVE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + STUDENT_OBJECTIVE_TABLE
            + "(" + COLUMN_STUDENT_OBJECTIVE_STUDENT_ID + " INTEGER REFERENCES " + STUDENT_TABLE + " (" + COLUMN_STUDENT_ID + "), "
            + COLUMN_STUDENT_OBJECTIVE_OBJECTIVE_ID + " INTEGER REFERENCES " + OBJECTIVE_TABLE + "( " + COLUMN_OBJECTIVE_ID + " ),"
            +  COLUMN_STUDENT_OBJECTIVE_DATE_ACHIEVED + " TEXT ," + COLUMN_STUDENT_OBJECTIVE_TITLE + " TEXT, PRIMARY KEY(" + COLUMN_STUDENT_OBJECTIVE_STUDENT_ID
            + ", " + COLUMN_STUDENT_OBJECTIVE_OBJECTIVE_ID + " ))";
    public static final String DROP_STUDENT_OBJECTIVE_TABLE_QUERY = "DROP TABLE IF EXISTS " + STUDENT_OBJECTIVE_TABLE;


    // SNACK TABLE
    public static final String SNACK_TABLE = "snack";
    public static final String COLUMN_SNACK_ID = "snack_id";
    public static final String COLUMN_SNACK_NAME = "name";
    public static final String COLUMN_SNACK_PRICE = "price";
    public static final String COLUMN_SNACK_CATEGORY = "category";
    public static final String COLUMN_SNACK_CALORIES = "calories";

    public static final String CREATE_SNACK_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + SNACK_TABLE
            + "(" + COLUMN_SNACK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_SNACK_NAME + " TEXT," +
            COLUMN_SNACK_PRICE + " DOUBLE," + COLUMN_SNACK_CATEGORY + " TEXT, " + COLUMN_SNACK_CALORIES + " INTEGER))";
    public static final String DROP_SNACK_TABLE_QUERY = "DROP TABLE IF EXISTS " + SNACK_TABLE;



    //  POINTS TRANSACTION TABLE
    public static final String TRANSACTION_TABLE = "transactions";

    public static final String COLUMN_TRANSACTION_ID = "transaction_id";

    public static final String COLUMN_TRANSACTION_TITLE = "title";
    public static final String COLUMN_TRANSACTION_RECIPIENT_ID = "recipient_id";
    public static final String COLUMN_TRANSACTION_SENDER_ID = "sender_id";
    public static final String COLUMN_TRANSACTION_TYPE = "type";

    public static final String COLUMN_TRANSACTION_POINT_AMOUNT = "point_amount";

    public static final String COLUMN_TRANSACTION_CREATED_AT = "created_at";

    public static final String COLUMN_TRANSACTION_STATUS = "status";

    public static final String COLUMN_TRANSACTION_STUDENT_ID = "student_id";

    public static final String CREATE_TRANSACTION_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + TRANSACTION_TABLE + "(" + COLUMN_TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_TRANSACTION_TITLE + " TEXT,"  + COLUMN_TRANSACTION_RECIPIENT_ID + " INTEGER,"
            + COLUMN_TRANSACTION_SENDER_ID + " INTEGER," + COLUMN_TRANSACTION_TYPE + " TEXT,"
            + COLUMN_TRANSACTION_POINT_AMOUNT + " INTEGER," + COLUMN_TRANSACTION_CREATED_AT + " TEXT," + COLUMN_TRANSACTION_STATUS + " INTEGER,"
            + COLUMN_TRANSACTION_STUDENT_ID + " INTEGER REFERENCES " + STUDENT_TABLE + "(" + COLUMN_STUDENT_ID + "))";

    public static final String DROP_TRANSACTION_TABLE_QUERY = "DROP TABLE IF EXISTS " + TRANSACTION_TABLE;


    // POINTS HISTORY TABLE
    public  static final String POINT_BALANCE_HISTORY_TABLE = "points_history";

    public static final String COLUMN_POINT_BALANCE_HISTORY_ID = "point_balance_history_id";

    public static final String COLUMN_POINT_BALANCE_HISTORY_DATE = "date";

    public  static final String COLUMN_POINT_BALANCE_HISTORY_POINTS_BALANCE = "points_balance";

    public static final String COLUMN_POINT_BALANCE_HISTORY_STUDENT_ID = "student_id";

    public static final String CREATE_POINT_BALANCE_HISTORY_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + POINT_BALANCE_HISTORY_TABLE +
            "(" + COLUMN_POINT_BALANCE_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_POINT_BALANCE_HISTORY_DATE + " TEXT,"
            + COLUMN_POINT_BALANCE_HISTORY_POINTS_BALANCE + " INTEGER, "
            + COLUMN_POINT_BALANCE_HISTORY_STUDENT_ID + " INTEGER REFERENCES " + STUDENT_TABLE + " (" + COLUMN_STUDENT_ID + "))";

    public static final String DROP_POINT_BALANCE_HISTORY_QUERY = "DROP TABLE IF EXISTS " + POINT_BALANCE_HISTORY_TABLE;


    // POINTS HISTORY TABLE

}
