package com.ohb.app.util.api;

public class APIName {

	// version
    public static final String VERSION = "api/v1/{companyId}";

    // charset
    public static final String CHARSET = "application/json;charset=utf-8";

    // action user
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";

    // SSO
    public static final String OAUTH_LOGIN = VERSION + "oauth/login";
    public static final String OAUTH_IMPLICIT_LOGIN = VERSION + "oauth/implicit/login";
    public static final String CHECK_API_KEY = VERSION + "oauth";
    
 // Hotel api links
    public static final String HOTEL = "/hotels";
    public static final String HOTEL_BY_NAME = "/{hotelname}";
    public static final String HOTEL_BY_ID = "/{productId}";
    public static final String HOTEL_BY_IDS = "/list";
    public static final String PRODUCTS_FILTER_LIST = "/filter";
//    public static final String HOTEL_DETAILS = VERSION + "/{product_id}";
//    public static final String PRODUCT_ATTRIBUTES = VERSION + "/productattributes";
    
  //user api link
    public static final String USERS = "/users";
    public static final String USERS_REGISTER = "/register";
    public static final String USERS_LOGIN = "/login";
    public static final String USERS_LOGOUT = "/logout";

    //review api link
    public static final String REVIEWS = VERSION + "/reviews";
    public static final String REVIEWS_BY_HOTEL_ID = "/{id}";
    public static final String REVIEWS_ADD = "/add";
    
 // rooms api links
    public static final String ROOMS = VERSION + "/rooms";
    public static final String ROOMS_ID = VERSION + "/{id}";
    
  //BOOKINGS
    public static final String BOOKINGS = VERSION + "bookings";
    public static final String BOOKINGS_BY_COMPANY = "/{id}";
}
