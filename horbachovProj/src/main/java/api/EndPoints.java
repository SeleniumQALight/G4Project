package api;



public interface EndPoints {
    String baseUrl = "http://qa-complex-app-for-testing.herokuapp.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{0}";
    String privatUrl = "https://api.privatbank.ua";
    String EXCHANGE_RATES = privatUrl + "/p24api/pubinfo?exchange&json&coursid={0}";


    String LOGIN = baseUrl + "/api/login";
    String CREATE_POST = baseUrl + "/api/create-post";
    String DELETE_POST = baseUrl + "/api/post/{0}";
}
