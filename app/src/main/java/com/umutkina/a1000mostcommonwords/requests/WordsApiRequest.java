package com.umutkina.a1000mostcommonwords.requests;


import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by umutkina on 27/10/14.
 */
public interface WordsApiRequest {


    //    @POST("/users/forgot-password")
//    @FormUrlEncoded
//    void forgotPassword(@Field("email") String email, Callback<ActionResult> projectCallback);
//
    @GET("/{word}")
    void words(@Path("word") String word, Callback<Response> projectCallback);
//
//    @POST("/users/update-password")
//    @FormUrlEncoded
//    void updatePassword(@Field("oldPassword") String oldPassword, @Field("newPassword") String newPAssrowd, Callback<ActionResult> projectCallback);
//
//
//    @POST("/projects/log/attempt-project-pledge")
//    @FormUrlEncoded
//    void projectPledge(@Field("projectId") long projectId, Callback<Response> projectCallback);
//
//    @POST("/projects/log/view-project-page")
//    @FormUrlEncoded
//    void viewProject(@Field("projectId") long projectId, Callback<Response> projectCallback);
//
//
//
//    @GET("/projects/mobile")
//    void getProjects(@Nullable @Query("page") int page, Callback<ArrayList<MobileListItem>> arrayListCallback);
//
//    @GET("/projects/search/mobile")
//    void getSearchProjects(@Nullable @Query("query") String query, Callback<ArrayList<MobileListItem>> arrayListCallback);
//
//    @GET("/misc/force-update-check")
//    void forceUpdate(@Query("platform") int platform, @Query("version") String version,
//                     Callback<ForceUpdate> forceUpdate);
//
//
//    @GET("/projects/mobile/about-to-finish")
//    void getAboutToFinish(Callback<ArrayList<MobileListItem>> arrayListCallback);
//
//    @GET("/projects/mobile/popular")
//    void getPopularProjects(Callback<ArrayList<MobileListItem>> arrayListCallback);
//
//    @GET("/projects/mobile/favorites")
//    void getFavorites(Callback<ArrayList<MobileListItem>> arrayListCallback);
//
//    @GET("/messages/conversations")
//    void getMessages(Callback<ArrayList<Conversation>> arrayListCallback);
//
//    @GET("/users/backed-project-activities")
//    void getMyActivities(Callback<ArrayList<Activity>> arrayListCallback);
//
//    @GET("/projects/{projectId}/activities")
//    void getActivitiesByProjectId(@Path("projectId") long projectId, Callback<ArrayList<Activity>> arrayListCallback);
//
//    @GET("/projects/{projectId}/am-i-backer")
//    void amIBacker(@Path("projectId") long projectId, Callback<ActionResult> actionResultCallback);
//
//    @POST("/projects/{projectId}/add-to-favorites")
//    void addFavorite(@Path("projectId") long projectId, Callback<ActionResult> actionResultCallback);
//
//    @GET("/users/backed-projects")
//    void getBackedProjects(Callback<ArrayList<Backing>> arrayListCallback);
//
//    @GET("/users/me")
//    void getMyUser(Callback<User> arrayListCallback);
//
//    @GET("/projects/first-quick-ratee")
//    void getQuickRateProject(Callback<Project> arrayListCallback);
//
//    @GET("/projects/{projectId}")
//    void getProjectById(@Path("projectId") long projectId, Callback<Project> projectCallback);
//
//    @GET("/projects/{projectId}/comments")
//    void getCommentsById(@Path("projectId") long projectId, Callback<ArrayList<Comment>> projectCallback);
//
//    @GET("/projects/mobile/favorites")
//    void getMobileFavorites(Callback<ArrayList<MobileListItem>> arrayListCallback);
//
//    @POST("/projects/{projectId}/comments/create")
//    @FormUrlEncoded
//    void commentsCreate(@Path("projectId") long projectId, @Field("comment") String message, Callback<Response> projectCallback);
//
//    @POST("/messages/create")
//    @FormUrlEncoded
//    void messageCreate(@Field("counterpartId") long counterpartId, @Field("message") String message, Callback<Response> projectCallback);
//
//    @POST("/projects/{projectId}/quick-rate")
//    @FormUrlEncoded
//    void quickRate(@Path("projectId") long projectId, @Field("rate") int rate, @Field("comment") String comment, Callback<Project> projectCallback);
//
//    @POST("/users/signup")
//    @FormUrlEncoded
//    void signup(@Field("name") String name, @Field("surname") String surname, @Field("email") String email, @Field("password") String password, @Field("phone") String phone, @Nullable @Field("image") String image, @Nullable @Field("facebookId") String facebookId, @Nullable @Field("fbAccesToken") String fbAccesToken, Callback<LoginSignupResponse> projectCallback);
//
//    @Multipart
//    @POST("/misc/upload-image")
//    void uploadImage(@Part("image") TypedFile image, Callback<Image> imageUrl);
//
//    @GET("/misc/autocomplete-city")
//    ArrayList<City> getCities(@Query("query") String query);
//
//    @POST("/users/login")
//    @FormUrlEncoded
//    void login(@Field("email") String email, @Field("password") String password, Callback<LoginSignupResponse> projectCallback);
//
//    @POST("/users/connect-facebook")
//    @FormUrlEncoded
//    void loginWithFacebook(@Field("facebookId") String facebookId, @Field("fbAccessToken") String fbAccessToken, Callback<User> userCallback);
//
//    @POST("/users/update")
//    void updateUser(@Body User user, Callback<User> userCallback);
//
//    @POST("/projects/pay-secure")
//    @FormUrlEncoded
//    void paySecure(@Nullable @Field("ccName") String ccName, @Nullable @Field("ccNumber") String ccNumber, @Nullable @Field("ccMonth") String ccMonth, @Nullable @Field("ccYear") String ccYear, @Nullable @Field("ccCvv") String ccCvv, @Field("useSavedCC") boolean useSavedCC, @Field("ccRemember") boolean ccRemember, @Field("password") String password, @Field("address") String address, @Field("cityId") String cityId, @Field("title") String title, @Field("taxNo") String taxNo, @Field("taxOffice") String taxOffice, @Field("projectId") String projectId, @Field("rewardId") String rewardId, @Field("amount") String amount, Callback<ActionResult> projectCallback);
//
//    @POST("/projects/pay-anon")
//    @FormUrlEncoded
//    void payAnon(@Field("ccName") String ccName, @Field("ccNumber") String ccNumber, @Field("ccMonth") String ccMonth, @Field("ccYear") String ccYear, @Field("ccCvv") String ccCvv, @Field("address") String address, @Field("cityId") String cityId, @Field("title") String title, @Field("taxNo") String taxNo, @Field("taxOffice") String taxOffice, @Field("projectId") String projectId, @Field("rewardId") String rewardId, @Field("amount") String amount, @Field("email") String email, @Field("phone") String phone, Callback<ActionResult> projectCallback);
//
//    @GET("/projects/mobile/friends-backings")
//    void getSupportedProjectByFriends(Callback<ArrayList<MobileListItem>> listCallback);
//    @POST("/signup")
//    void signup(@Body DriverSignupRequest body, Callback<DriverLoginSignupResponse> signupResponse);
//    @POST("/update-profile")
//    void update(@Body DriverUpdteRequest body, Callback<DriverProfileUpdateResponse> signupResponse);
//    @POST("/login")
//    void login(@Body DriverLoginRequest body, Callback<DriverLoginSignupResponse> loginResponse);
//
//    @POST("/location")
//    void location(@Body DriverLocationRequest body, Callback<Response> loginResponse);
//
//    @GET("/available-freights")
//    void getAvaliableFreights(@Nullable @Query("olderThan") String olderThan, @Nullable @Query("newerThan") String newerThan, Callback<DriverAvailableFreightsResponse> driverAvailableFreightsResponseCallback);
//
//    @GET("/my-requests")
//    void getMyRequests(@Nullable @Query("olderThan") String olderThan, @Nullable @Query("newerThan") String newerThan, Callback<DriverFreightRequestsResponse> driverAvailableFreightsResponseCallback);
//
//    @GET("/feed")
//    void getFeed(@Nullable @Query("olderThan") String olderThan, @Nullable @Query("newerThan") String newerThan, Callback<DriverFeedResponse> driverFeedResponseCallback);
//    @POST("/feed")
//    void addFeed(@Body DriverFeedRequest body, Callback<DriverFeedItemPostResponse> loginResponse);
//    @POST("/freights/{freightId}/request")
//    void freightReq(@Path("freightId") long freightId, Callback<Response> loginResponse);
//
//    @POST("/freights/{freightId}/deliver")
//    void freightDeliver(@Path("freightId") long freightId, @Body DriverFreightPickupRequest driverFreightPickupRequest, Callback<Response> loginResponse);
//
//    @POST("/freights/{freightId}/pickup")
//    void freightPickup(@Path("freightId") long freightId, @Body DriverFreightPickupRequest driverFreightPickupRequest, Callback<Response> loginResponse);
//
//    @GET("/venues")
//    void getVenues(@Query("latitude") double latitude, @Query("longitude") double longitude, Callback<DriverVenuesResponse> driverAvailableFreightsResponseCallback);

    //    @GET("/content/offers.json")
//    void getOffers(@Nullable @Header("x-prof-token") String authorization, Callback<MobileOfferResponse> offersResponse);
//
////    @GET("/offers.json")
////    void  getOffers(Callback<MobileOfferResponse> offersResponse);
//
//    @FormUrlEncoded
//    @POST("/content/answer-quiz.json")
//    void answerQuiz(@Nullable @Header("x-prof-token") String authorization, @Field("tags") String tags, @Field("answerId") int answerId, Callback<SurveyPoll> pollSurvey);
//
//
//    @FormUrlEncoded
//    @POST("/content/answer-poll.json")
//    void answerPoll(@Nullable @Header("x-prof-token") String authorization, @Field("tags") String tags, @Field("answerId") int answerId, Callback<SurveyPoll> pollSurvey);
//
//    @FormUrlEncoded
//    @POST("/content/answer-survey.json")
//    void answerSurvey(@Nullable @Header("x-prof-token") String authorization, @Field("tags") String tags, @Field("answerIdCommaSeperated") String answerIdCommaSeperated, Callback<ArrayList<SurveyPoll>> pollSurvey);
//
//    @FormUrlEncoded
//    @POST("/content/do-get-code.json")
//    void getCode(@Nullable @Header("x-prof-token") String authorization, @Field("pennaId") String pennaId, @Field("badgeTrophyId") String badgeTrophyId, @Field("crowdBehavior") String crowdBehavior, @Field("urlPostfix") String urlPostfix, Callback<CodeGenerationResult> codeGenerationResult);
//
//    @FormUrlEncoded
//    @POST("/content/do-claim-offer.json")
//    void doClaim(@Nullable @Header("x-prof-token") String authorization, @Field("key") String key, @Nullable @Field("badgeTrophyId") String badgeTrophyId, @Nullable @Field("crowdBehavior") String crowdBehavior, @Nullable @Field("urlPostfix") String urlPostfix, Callback<CodeGenerationResult> codeGenerationResult);
//
//    @POST("/user/do-login.json")
//    void doLogin(@Nullable @Header("x-prof-token") String authorization, Callback<MobileResponse> mobileResponse);
//
//    @POST("/user/do-save-push-notif.json")
//    void doSavePush(@Nullable @Header("x-prof-token") String authorization, @Field("text") String text, @Field("type") String type, @Field("url") String url, Callback<Response> mobileResponse);
//
//    @FormUrlEncoded
//    @POST("/content/do-page-view.json")
//    void doPageView(@Nullable @Header("x-prof-token") String authorization, @Nullable @Field("page") String page, Callback<Response> response);
//
//
//    @GET("/content/app-texts.json")
//    void getAppTexts(@Nullable @Header("x-prof-token") String authorization, Callback<HashMap<String, String>> offersResponse);
//
//
//    @FormUrlEncoded
//    @POST("/user/send-complaint.json")
//    void sendMessage(@Nullable @Header("x-prof-token") String authorization, @Field("nprd") String nprd, @Field("title") String title, @Field("email") String email, @Field("message") String message, Callback<Response> codeGenerationResult);
//
//    @FormUrlEncoded
//    @POST("/content/do-share-completed.json")
//    void shareCompleted(@Nullable @Header("x-prof-token") String authorization, @Field("platform") Integer platform, @Field("key") String key, @Field("urlPrefix") String urlPrefix, Callback<NotificationWrapped> notificationWrapped);
//
//    @GET("/user/do-check-booost-decision.json")
//    void checkBoostDesicion(@Nullable @Header("x-prof-token") String authorization, @Query("lastBill") Double lastBill, @Query("postpaid") boolean postpaid, Callback<BoostDecision> notificationWrapped);
//
//    @GET("/user/do-get-notifications.json")
//    void doGetNotification(@Nullable @Header("x-prof-token") String authorization, Callback<NotificationWrapped> notificationWrapped);
//    @GET("/api/content/is-alive.json")
//    void isAlive(Callback<Response> notificationWrapped);


}
