package fundroid.ixicode.base;

/**
 * Created by Sagar Sagar Verma on 16-03-2017.
 **/

public class Apis {

    private static String BASE_URL = "http://build2.ixigo.com/";
    public static final String URL_RECOMENDED = BASE_URL + "api/v2/widgets/brand/inspire?product=1&apiKey=ixicode!2$";
    public static final String URL_CITY_SUGGEST = BASE_URL + "action/content/zeus/autocomplete?searchFor=tpAutoComplete&neCategories=City&query=<Q>";
    public static final String URL_CITY_POINTS = BASE_URL + "api/v3/namedentities/city/cityId/categories?apiKey=ixicode!2$&type=<TYPE>&skip=<SKIP>&limit=<LIMIT>";

}