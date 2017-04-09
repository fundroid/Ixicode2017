package fundroid.ixicode.base;

/**
 * Created by Sagar Sagar Verma on 16-03-2017.
 **/

public class Apis {

    public static String RSymbol = "₹";

    public static String[] pointTypes = {"Places To Visit", "Hotel", "Accomodations"};
    public static final String BASE_URL = "http://build2.ixigo.com";
    public static final String URL_RECOMENDED = BASE_URL + "/api/v2/widgets/brand/inspire?product=1&apiKey=ixicode!2$";
    public static final String URL_CITY_SUGGEST = BASE_URL + "/action/content/zeus/autocomplete?searchFor=tpAutoComplete&neCategories=City&query=<Q>";
    public static final String URL_CITY_POINTS = BASE_URL + "/api/v3/namedentities/city/<CITY>/categories?apiKey=ixicode!2$&type=<TYPE>&skip=<SKIP>&limit=<LIMIT>";
    public static final String URL_ENT_DETAIL = BASE_URL + "/api/v3/namedentities/id/<ENT>?apiKey=ixicode!2$";

    public static final String URL_FOR_MAP = "https://maps.googleapis.com/maps/api/staticmap?center=<LAT>,<LNG>&zoom=7&size=400x300&key=AIzaSyAl1htxIonUsb4vRjQgljDf0dWU03g3oCY";
}