package api;

public interface EndPoints {
    String baseUrl = "http://qa-complex-app-for-testing.herokuapp.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{0}";
    String privatUrl = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11";

}
