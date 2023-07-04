package com.example.kumarGroup;


import com.example.kumarGroup.DealstageAK.Notes_POJO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public class WebService {
    private static WebServiceInterface webApiInterface;

    public static WebServiceInterface getClient() {
        if (webApiInterface == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okclient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                   /* .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)*/
                    .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                    .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                    .readTimeout(5, TimeUnit.MINUTES) // read timeout


                    /*.connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)*/
                    .build();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .setLenient()
                    .create();

            // Post post = gson.fromJson(reader, Post.class);

            Retrofit client = new Retrofit.Builder()
                    .baseUrl("https://kumargroup.asworldtech.com/api/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okclient)
                    .build();

            webApiInterface = client.create(WebServiceInterface.class);
        }
        return webApiInterface;
    }

    public interface WebServiceInterface {
        @POST("cat_list.php")
        Call<CategoryModel> getcatlist();

        @POST("state_list.php")
        Call<StateModel> getstate();

        @POST("city.php")
        @FormUrlEncoded
        Call<DataCityModel> getCity(
                @Field("so_state") String so_state
        );

        @POST("district.php")
        @FormUrlEncoded
        Call<DataDistrictModel> getDistrict(
                @Field("city") String city
        );

        @POST("tehsill.php")
        @FormUrlEncoded
        Call<DataTehsilModel> getTeshill(
                @Field("district") String city
        );

        @POST("village.php")
        @FormUrlEncoded
        Call<DataVillageModel> getVillage(
                @Field("tehsil") String city
        );


        @POST("data_list_count.php")
        @FormUrlEncoded
        Call<DataCountListModel> getcount(
                @Field("emp") String emp
        );

        @POST("emp.php")
        @FormUrlEncoded
        Call<DatafonameModel> getfoname(
                @Field("village") String village/*,
                @Field("cat_id") String cat_id,
                @Field("flag") String flag*/
        );

        @POST("emp.php")
        @FormUrlEncoded
        Call<DatafonameModel> getEMPname(
                @Field("village") String village,
                @Field("cat_id") String cat_id,
                @Field("flag") String flag
        );

        @POST("ssp_databank_add.php")
        @FormUrlEncoded
        Call<DataSubmitModel> ssp_getdata(
                @Field("login_id") String login_id,
                @Field("cat_id") String cat_id,
                @Field("f_name") String f_name,
                @Field("l_name") String l_name,
                @Field("state") String state,
                @Field("city") String city,
                @Field("distric") String distric,
                @Field("tehsil") String tehsil,
                @Field("vilage") String vilage,
                @Field("emp") String emp,
                @Field("mobile") String mobile,
                @Field("whatsappno") String whatsappno,
                @Field("desc") String desc,
                @Field("c_status") String c_status,
                @Field("next_date") String next_date,
                @Field("type") String type,
                @Field("follow_type") String follow_type
        );

        @POST("cat_add.php")
        @FormUrlEncoded
        Call<DataSubmitModel> emp_getdata(
                @Field("login_id") String login_id,
                @Field("cat_id") String cat_id,
                @Field("f_name") String f_name,
                @Field("l_name") String l_name,
                @Field("state") String state,
                @Field("city") String city,
                @Field("distric") String distric,
                @Field("tehsil") String tehsil,
                @Field("vilage") String vilage,
                @Field("emp") String emp,
                @Field("mobile") String mobile,
                @Field("whatsappno") String whatsappno,
                @Field("desc") String desc,
                @Field("c_status") String c_status,
                @Field("next_date") String next_date,
                @Field("type") String type,
                @Field("follow_type") String follow_type,
                @Field("model") String model,
                @Field("make_name") String make_name,
                @Field("sor_of_inq") String sor_of_inq,
                @Field("model_y") String model_y,
                @Field("inq_date") String inq_date
        );

        @POST("cat_detail.php")
        @FormUrlEncoded
        Call<DataCatagoryDetailModeljava> getDetail(
                @Field("cat_id") String cat_id,
                @Field("emp") String emp

        );

        @POST("forgot_password.php")
        @FormUrlEncoded
        Call<Forgotpassword> forgotPassword(
                @Field("mobile") String mobile
        );

        @POST("reset_password_otp_match.php")
        @FormUrlEncoded
        Call<Forgotpassword> verifyOtp(
                @Field("mobile") String mobile,
                @Field("otp") String otp
        );

        @POST("reset_password_update.php")
        @FormUrlEncoded
        Call<Forgotpassword> resetPassword(
                @Field("mobile") String mobile,
                @Field("password") String password
        );

        @POST("login.php")
        @FormUrlEncoded
        Call<DataLoginModel> getLogin(
                @Field("mobile") String mobile,
                @Field("password") String password,
                @Field("type") String type,
                @Field("imei_no") String imei_no,
                @Field("token") String token
        );


        @POST("vendor_login.php")
        @FormUrlEncoded
        Call<VendorLoginModel> getLoginVendor(
                @Field("mobile") String mobile,
                @Field("password") String password,
                @Field("type") String type
        );


        @POST("app_insert.php")
        @Multipart
        Call<ChackModelclass> getchackin(
                @Part("emp") RequestBody emp,
                @Part MultipartBody.Part image8,
                @Part("address") RequestBody address
        );

        @POST("task_demo.php")
        @FormUrlEncoded
        Call<DataTaskModel> gettask(
                @Field("emp") String emp,
                @Field("task_type") String task_type
        );

        @POST("replace_no.php")
        @FormUrlEncoded
        Call<DataReaplceModel> getreplacenumber(
                @Field("emp") String emp,
                @Field("cat_id") String cat_id,
                @Field("cid") String cid,
                @Field("type") String type,
                @Field("task_type") String task_type
        );

        @POST("attendance.php")
        @FormUrlEncoded
        Call<WalletModel> getdataattendancewallet(
                @Field("emp_id") String emp_id,
                @Field("fdate") String fdate,
                @Field("tdate") String tdate

        );

        @POST("emp_ledger_list.php")
        @FormUrlEncoded
        Call<ladger_getdata_model> AccountLadgerData(
                @Field("emp") String emp_id

        );


        @POST("emp_village.php")
        @FormUrlEncoded
        Call<DataVillageeModel> getVillagee(
                @Field("emp") String emp
        );

        @POST("removephone.php")
        @FormUrlEncoded
        Call<DataRemoveCallModel> removeCall(
                @Field("call_id") String call_id,
                @Field("mobile") String mobile,
                @Field("whatsappno") String whatsappno,
                @Field("emp_id") String emp_id,
                @Field("task_type") String task_type
        );

        @POST("removephone.php")
        @FormUrlEncoded
        Call<RemoveWhatappModel> removeWhapp(
                @Field("call_id") String call_id,
                @Field("mobile") String mobile,
                @Field("whatsappno") String whatsappno,
                @Field("emp_id") String emp_id,
                @Field("task_type") String task_type
        );

        @POST("remove_assigment.php")
        @FormUrlEncoded
        Call<DataremoveassignmentModel> removeCallassignment(
                @Field("call_id") String call_id,
                @Field("mobile") String mobile,
                @Field("whatsappno") String whatsappno,
                @Field("emp_id") String emp_id,
                @Field("task_type") String task_type
        );

        @POST("remove_assigment.php")
        @FormUrlEncoded
        Call<DataremoveassignmentModel> removewhappassignment(
                @Field("call_id") String call_id,
                @Field("mobile") String mobile,
                @Field("whatsappno") String whatsappno,
                @Field("emp_id") String emp_id,
                @Field("task_type") String task_type
        );

        @POST("task_complete.php")
        @FormUrlEncoded
        Call<DataTaskCompletModel> getcompettask(
                @Field("emp") String emp,
                @Field("id") String id
        );

        @POST("lunch.php")
        @Multipart
        Call<LanchBreckModel> getlanchbrack(
                @Part("emp") RequestBody emp,
                @Part("lunchtime") RequestBody lunchtime,
                @Part("id") RequestBody id
        );

        @POST("lunch_continue.php")
        @Multipart
        Call<LanchBreckModel2> getlanchbrack2(
                @Part("emp") RequestBody emp,
                @Part("lunchtime") RequestBody lunchtime,
                @Part("id") RequestBody id
        );

        @POST("checkout.php")
        @Multipart
        Call<DataChackOutModel> getChackout(
                @Part("emp") RequestBody emp,
                @Part("id") RequestBody id,
                @Part MultipartBody.Part image1
        );

        @POST("emp_image_upload.php")
        @Multipart
        Call<DataSubmitModel> uploadImageProfile(
                @Part("emp") RequestBody emp,
                @Part MultipartBody.Part image1
        );

        @POST("checkout_no.php")
        @Multipart
        Call<DataChackOutModelno> getChackoutno(
                @Part("emp") RequestBody emp,
                @Part("id") RequestBody id,
//                @Part("location") RequestBody location,
                @Part MultipartBody.Part image1
        );

        @POST("overtime.php")
        @Multipart
        Call<DataTaskOverTimeModel> getOvertime(
                @Part("emp") RequestBody emp,
                @Part("id") RequestBody id,
                @Part MultipartBody.Part image2
        );

        @POST("overtime_end.php")
        @Multipart
        Call<DataEndOvertimemoel> getEndovertime(
                @Part("emp") RequestBody emp,
                @Part("id") RequestBody id,
                @Part MultipartBody.Part image3
        );

        @POST("absent_reason.php")
        @FormUrlEncoded
        Call<AbsentresonModel> getabsent(
                @Field("reason") String reason,
                @Field("id") String id
        );

        @POST("check_phase.php")
        @FormUrlEncoded
        Call<ChackphaseModel> getphase(
                @Field("id") String id
        );

        @POST("halfday_checkin.php")
        @Multipart
        Call<DataHalfDayChackinModel> gethalfdaychackin(
                @Part("emp") RequestBody emp,
                @Part("id") RequestBody id,
                @Part MultipartBody.Part image4
        );

        @POST("halfday_checkout.php")
        @Multipart
        Call<DataHalfDayChackOutModel> gethalfdaychackout(
                @Part("emp") RequestBody emp,
                @Part("id") RequestBody id,
                @Part MultipartBody.Part image5
        );

        @POST("halfsunday_checkin.php")
        @Multipart
        Call<DataHalfSundayChackinModel> gethalfsundaychacin(
                @Part("emp") RequestBody emp,
                @Part("id") RequestBody id,
                @Part MultipartBody.Part image6
        );

        @POST("halfsunday_checkout.php")
        @Multipart
        Call<DataHalfSundayChackoutModel> gethalfsundaychackout(
                @Part("emp") RequestBody emp,
                @Part("id") RequestBody id,
                @Part MultipartBody.Part image7
        );

        //New Work
        @POST("salarysleep.php")
        @FormUrlEncoded
        Call<SalarySlipModel> getSalarySlip(
                @Field("emp_id") String emp_id,
                @Field("fdate") String fdate,
                @Field("tdate") String tdate
        );

        @POST("loan.php")
        @FormUrlEncoded
        Call<AddLoanModel> getAddLoan(
                @Field("emp_id") String emp_id,
                @Field("payment_type") String loan_type,
                @Field("type") String type,
                @Field("l_amount") String l_amount,
                @Field("description") String description,
                @Field("cdate") String cdate
        );


        @POST("main_type.php")
        @FormUrlEncoded
        Call<MaintananceEmpVenList> getMaintanaceEmpVenList(
                @Field("type") String type
        );


        @POST("main_add.php")
        @Multipart
        Call<MaintenanceAddModel> getMaintenanceAdd(
                @Part("emp_id") RequestBody emp_id,
                @Part("m_type") RequestBody m_type,
                @Part("type") RequestBody type,
                @Part("loan_type") RequestBody loan_type,
                @Part("m_name") RequestBody m_name,
                @Part("amount") RequestBody amount,
                @Part("mdate") RequestBody mdate,
                @Part("desc") RequestBody desc,
                @Part("gst_invoice ") RequestBody gst_invoice ,
                @Part MultipartBody.Part image8
        );


        @POST("main_display.php")
        @FormUrlEncoded
        Call<DisplayMaintenanceModel> getDisplayMaintenance(
                @Field("emp_id") String emp_id,
                @Field("fdate") String fdate,
                @Field("tdate") String tdate
        );


        /*@POST("loan_display.php")
        @FormUrlEncoded
        Call<DisplayLoanModel> getDisplayLoan(
                @Field("emp_id")String emp_id,
                @Field("fdate")String fdate,
                @Field("tdate")String tdate
        );*/

        @POST("loan_display.php")
        @FormUrlEncoded
        Call<DisplayLoanFinalModel> getDisplayLoanFinal(
                @Field("emp_id") String emp_id,
                @Field("fdate") String fdate,
                @Field("tdate") String tdate
        );


        @GET("f_category.php")
        Call<CategoryFollowUpModel> getCategoryFollowUp();


        @POST("f_customer.php")
        @FormUrlEncoded
        Call<CustomerFollowUpModel> getCustomerFollowUp(
                @Field("cat_id") String cat_id,
                @Field("emp_id") String emp_id
        );


        @POST("followup.php")
        @FormUrlEncoded
        Call<FollowUpSubmitModel> getFollowUpSubmit(
                @Field("login_id") String login_id,
                @Field("cat_id") String cat_id,
                @Field("sname") String sname,
                @Field("reason") String reason,
                @Field("booking") String booking,
                @Field("booking_amt") String booking_amt,
                @Field("delivry") String delivry,
                @Field("model_name") String model_name,
                @Field("payment_collection") String payment_collection,
                @Field("amount") String amount,
                @Field("vdate") String vdate,
                @Field("vdays") String vdays,
                @Field("addgcust") String addgcust,
                @Field("sell_lost") String sell_lost,
                @Field("sell_model") String sell_model
        );


        @POST("monthly_en.php")
        @FormUrlEncoded
        Call<MonthFollowUpModel> getMonthFollowUp(
                @Field("emp") String emp
        );


        @POST("weekly_en.php")
        @FormUrlEncoded
        Call<DayFollowUpModel> getDayFollowUp(
                @Field("emp") String emp
        );

        @POST("weekly_en.php")
        @FormUrlEncoded
        Call<WeeklyFollowUpModel> getWeeklyFollowUp(
                @Field("emp") String emp
        );


        @POST("daliy_en.php")
        @FormUrlEncoded
        Call<DailyFollowUpModel> getDailyFollowUp(
                @Field("emp") String emp
        );


        @POST("monthly_en_detail.php")
        @FormUrlEncoded
        Call<ShowMonthModel> getShowMonthFollowUp(
                @Field("emp") String emp,
                @Field("id") String id
        );


        @GET("employee_work.php")
        Call<EmployeeListWBModel> getEmployeeWB();


        @POST("customer_work.php")
        @FormUrlEncoded
        Call<CustomerListWBModel> getCustomerListWB(
                @Field("date") String date,
                @Field("emp_id") String emp_id
        );

        @POST("followup_sms.php")
        @FormUrlEncoded
        Call<CurrentDateModel> getCurrentDate(
                @Field("date") String date
        );


        @POST("workbook.php")
        @FormUrlEncoded
        Call<WorkBookSubmitModel> getWorkBookSubmit(
                @Field("login_id") String login_id,
                @Field("emp") String emp,
                @Field("sname") String sname,
                @Field("reason") String reason,
                @Field("booking") String booking,
                @Field("booking_amt") String booking_amt,
                @Field("delivry") String delivry,
                @Field("model_name") String model_name,
                @Field("payment_collection") String payment_collection,
                @Field("amount") String amount
        );


        @POST("followp_new.php")
        @FormUrlEncoded
        Call<SubmitDetailShowFollowUpModel> getShowSubmitFollowUp(
                @Field("login_id") String login_id,
                @Field("cat_id") String cat_id,
                @Field("sname") String sname,
                @Field("reason") String reason,
                @Field("booking") String booking,
                @Field("booking_amt") String booking_amt,
                @Field("delivry") String delivry,
                @Field("model_name") String model_name,
                @Field("payment_collection") String payment_collection,
                @Field("amount") String amount,
                @Field("vdate") String vdate,
                @Field("vdays") String vdays,
                @Field("id") String id,
                @Field("addgcust") String addgcust,
                @Field("sell_lost") String sell_lost,
                @Field("sell_model") String sell_model
        );


        @POST("visitdetail.php")
        @FormUrlEncoded
        Call<VisitFollowUpModel> getVisitFollowUp(
                @Field("emp") String emp,
                @Field("sname") String sname
        );


        @POST("overtime.php")
        @Multipart
        Call<StartOverTimeModel> getStartOverTime(
                @Part("emp") RequestBody emp,
                @Part MultipartBody.Part image8,
                @Part("type") RequestBody type
        );


        @POST("overtime_end.php")
        @Multipart
        Call<OverTimeCheckOutModel> getOverTimeCheckOut(
                @Part MultipartBody.Part image3,
                @Part("id") RequestBody id
        );


        @POST("gn_cu.php")
        @FormUrlEncoded
        Call<ReportCollectionCustomerModel> getRCCategoryList(
                @Field("emp") String emp
        );


        @POST("gn_detail.php")
        @FormUrlEncoded
        Call<ShowDetailGVModel> getShowDetailRCGV(
                @Field("cat_id") String cat_id,
                @Field("emp") String emp
        );


        @POST("report_cel.php")
        @Multipart
        Call<OpenVisitRCGVModel> getOpenVisitRCGV(
                @Part("id") RequestBody id,
                @Part("login_id") RequestBody login_id,
                @Part("sname") RequestBody sname,
                @Part("cat_id") RequestBody cat_id,
                @Part("reason") RequestBody reason,
                @Part("booking") RequestBody booking,
                @Part("booking_amt") RequestBody booking_amt,
                @Part("delivry") RequestBody delivry,
                @Part("model_name") RequestBody model_name,
                @Part("payment_collection") RequestBody payment_collection,
                @Part("amount") RequestBody amount,
                @Part("vdate") RequestBody vdate,
                @Part("vdays") RequestBody vdays,
                @Part("addgcust") RequestBody addgcust,
                @Part("sell_lost") RequestBody sell_lost,
                @Part("sell_model") RequestBody sell_model,
                @Part("mobileno") RequestBody mobileno,
                @Part("add_id") RequestBody add_id,
                @Part("add_type") RequestBody add_type,
                @Part("location") RequestBody location,
                @Part MultipartBody.Part image1

        );


        @POST("rp_monthly.php")
            // @FormUrlEncoded
        Call<RcMonthModel> getRcMonth(
                // @Field("emp") String emp
        );


        @GET("rp_weekly.php")
        Call<RcWeeklyModel> getRcWeekly(
                /*  @Field("emp") String emp*/
        );


        @GET("rp_daliy.php")
        Call<DailyRCModel> getRcDaily(
                /*  @Field("emp") String emp*/
        );


        @POST("gn_mdwdetail.php")
        @FormUrlEncoded
        Call<AllEntryMonthWeekDayRCModel> getAllEntryMWD(
                @Field("id") String id
                //  @Field("emp") String emp
        );


        @POST("gr_detail.php")
        @FormUrlEncoded
        Call<ShowVisitRCModel> getVisitRC(
                /* @Field("emp") String emp,*/
                @Field("sname") String sname
        );

        @POST("gr_re_new.php")
        @Multipart
        Call<InsertRCSecondModel> getInsertRcSecond(
                @Part("id") RequestBody id,
                @Part("login_id") RequestBody login_id,
                @Part("cat_id") RequestBody cat_id,
                @Part("sname") RequestBody sname,
                @Part("reason") RequestBody reason,
//                @Part("booking") RequestBody booking,
                @Part("booking_amt") RequestBody booking_amt,
                @Part("delivry") RequestBody delivry,
                @Part("model_name") RequestBody model_name,
                @Part("payment_collection") RequestBody payment_collection,
                @Part("amount") RequestBody amount,
                @Part("vdate") RequestBody vdate,
                @Part("vdays") RequestBody vdays,
                @Part("addgcust") RequestBody addgcust,
                @Part("sell_lost") RequestBody sell_lost,
                @Part("sell_model") RequestBody sell_model,
                @Part("mobileno") RequestBody mobileno,
                @Part("add_type") RequestBody add_type,
                @Part("add_id") RequestBody add_id,
                @Part("location") RequestBody location,
                @Part MultipartBody.Part image1
        );

        @POST("notification_view.php")
        @FormUrlEncoded
        Call<NotificationViewModel> getNotificationView(
                @Field("emp") String emp
        );



        /*
        *  @POST("halfsunday_checkout.php")
        @Multipart
        Call<DataHalfSundayChackoutModel> gethalfsundaychackout(
                @Part("emp") RequestBody emp,
                @Part("id") RequestBody id,
                @Part  MultipartBody.Part image7

        );*/


        @POST("notification_img.php")
        @Multipart
        Call<PhotoUploadNotificationModel> getPhotoUploadNotification(
                @Part("emp") RequestBody emp,
                @Part("id") RequestBody id,
                @Part("add") RequestBody add,
                @Part("type") RequestBody type,
                @Part MultipartBody.Part image1
        );


        @POST("removenoti.php")
        @FormUrlEncoded
        Call<NotificationCallRemoveModel> getNotificationCallRemove(
                @Field("emp_id") String emp_id,
                @Field("call_id") String call_id,
                @Field("type") String type,
                @Field("mnumber") String mnumber
        );


        @GET("b_state.php")
        Call<addCustomerDetailStateModel> getCDState();


        @GET("maintainance_type_list.php")
        Call<MainTainanceType> getMaintananceList();


        @POST("b_city.php")
        @FormUrlEncoded
        Call<addCDCityModel> getCDCity(
                @Field("state") String state
        );


        @POST("b_district.php")
        @FormUrlEncoded
        Call<addCDDistrict> getDistrictCD(
                @Field("city") String city
        );


        @POST("b_tehsil.php")
        @FormUrlEncoded
        Call<addCDTalukaModel> getTalukaCD(
                @Field("district") String district
        );


//        @POST("b_village.php")
        @POST("test_b_village.php")
        @FormUrlEncoded
        Call<addVillageCDModel> getVillageCD(
                @Field("emp") String emp
        );


        @POST("booking_fetch.php")
        @FormUrlEncoded
        Call<MobileNumDetailModel> getDetailsOnMobileNo(
                @Field("mobile") String mobile
        );

        @POST("b_model.php")
        @FormUrlEncoded
        Call<ModelNameProductModel> getModelName(
                @Field("product") String product
        );


        @GET("b_make.php")
        Call<MakeDownPaymentModel> getMakeDownPayment();

        @POST("b_m_model.php")
        @FormUrlEncoded
        Call<ModelDownPaymentModel> getModelDownPayment(
                @Field("make") String make
        );


        @POST("booking_first.php")
        @Multipart
        Call<CustomerDetailFinalModel> getCustomerDetailNext(
                @Part("date") RequestBody date,
                @Part("emp") RequestBody emp,
                @Part("fname") RequestBody fname,
              /*  @Part("lname") RequestBody lname,*/
                @Part("sname") RequestBody sname,
                @Part("mobileno") RequestBody mobileno,
                @Part("whno") RequestBody whno,
                @Part("ref_name") RequestBody ref_name,
                @Part("ref_no") RequestBody ref_no,
                @Part("state") RequestBody state,
                @Part("city") RequestBody city,
                @Part("distric") RequestBody distric,
                @Part("tehsill") RequestBody tehsill,
                @Part("village") RequestBody village,
                @Part("emp_id") RequestBody emp_id,
                @Part("status") RequestBody status,
                @Part("type") RequestBody type,
                @Part("b_p_photo") RequestBody b_p_photo,
                @Part("check_photo") RequestBody check_photo,
                @Part("atype") RequestBody atype,
                @Part("empid") RequestBody empid,
                @Part("id") RequestBody id,
                @Part("inq_id") RequestBody inq_id,
                @Part MultipartBody.Part image1,
                @Part MultipartBody.Part image2,
                @Part MultipartBody.Part image3,
               /* @Part MultipartBody.Part image4,
                @Part MultipartBody.Part image5,*/
                @Part MultipartBody.Part image6,
                @Part MultipartBody.Part imgg1,
                @Part MultipartBody.Part imgg2,
                @Part MultipartBody.Part imgg3
        );


        @POST("booking_sec.php")
        @FormUrlEncoded
        Call<ProductDetailNextModel> getProductDetail(
                /* @Field("id") String id,*/
                @Field("product") String product,
                @Field("model") String model,
                @Field("desc") String desc,
//                @Field("other_option") String other_option,
                @Field("status") String status
        );


        //OtherProductModel
        @POST("booking_other.php")
        @FormUrlEncoded
        Call<OtherProductModel> getOtherProduct(
                @Field("product") String product,
                @Field("other_date") String other_date,
                @Field("other_amt") String other_amt,
                @Field("other_desc") String other_desc,
                @Field("other_option") String other_option,
                @Field("status") String status
        );


        @POST("booking_thired.php")
        @FormUrlEncoded
        Call<PriceDetailNextModel> getPriceDetailNext(
                @Field("deal_price") String deal_price,
                @Field("deal_price_in_word") String deal_price_in_word,
                @Field("gst") String gst,
                @Field("status") String status,
                @Field("id") String id
        );


        @POST("booking_four.php")
        @FormUrlEncoded
        Call<RTODetailNextModel> getRToDetailNext(
                @Field("id") String id,
                @Field("rto") String rto,
                @Field("rto_new") String rto_new,
                @Field("rto_tax") String rto_tax,
                @Field("rto_passing") String rto_passing,
                @Field("insurance") String insurance,
                @Field("agent_fee") String agent_fee,
                @Field("number_plat") String number_plat,
                @Field("loan_charge") String loan_charge,
                @Field("status") String status
        );


        @POST("booking_phase.php")
        @FormUrlEncoded
        Call<PhaseAddBookingModel> getPhaseAddBooking(
                @Field("id") String id
        );


        @POST("booking_five.php")
        @Multipart
        Call<DownPaymentNextModel> getDownPaymentNext(
                @Part("booking_amt") RequestBody booking_amt,
                @Part("amount") RequestBody amount,
                @Part("check_neft_rtgs") RequestBody check_neft_rtgs,
                @Part("check_date") RequestBody check_date,
                @Part("check_amt") RequestBody check_amt,
                @Part("neft_rtgs_date") RequestBody neft_rtgs_date,
                @Part("neft_rtgs_amt") RequestBody neft_rtgs_amt,
                @Part("make") RequestBody make,
                @Part("model") RequestBody model,
                @Part("year") RequestBody year,
                @Part("reg_no") RequestBody reg_no,
                @Part("old_amt") RequestBody old_amt,
                @Part("paper_expence") RequestBody paper_expence,
                @Part("c_amount") RequestBody c_amount,
                @Part("noc") RequestBody noc,
                @Part("status") RequestBody status,
                @Part("id") RequestBody id,
                @Part("amount_word") RequestBody amount_word,
                @Part("check_amt_word") RequestBody check_amt_word,
                @Part("neft_rtgs_amt_word") RequestBody neft_rtgs_amt_word,
                @Part("old_t_amt_word") RequestBody old_t_amt_word,
                @Part("total_amt") RequestBody total_amt,
                @Part MultipartBody.Part image1,
                @Part MultipartBody.Part image2,
                @Part MultipartBody.Part image3,
                @Part MultipartBody.Part image4,
                @Part MultipartBody.Part image5,
                @Part MultipartBody.Part image6,
                @Part MultipartBody.Part imgg6,
                @Part MultipartBody.Part imgg7
        );


        @POST("booking_six.php")
        @FormUrlEncoded
        Call<ConsumerSkimSubmitModel> getConsumerSkimSubmit(
                @Field("cskim") String cskim,
                @Field("skim") String skim,
                @Field("hood") String hood,
                @Field("toplink") String toplink,
                @Field("drowbar") String drowbar,
                @Field("toolkit") String toolkit,
                @Field("bumper") String bumper,
                @Field("hitech") String hitech,
                @Field("desc") String desc,
                @Field("status") String status,
                @Field("id") String id,
                @Field("skim_amt") String skim_amt,
                @Field("refal_val") String refal_val,
                @Field("refal_name") String refal_name,
                @Field("refal_mobile") String refal_mobile,
                @Field("refal_desc") String refal_desc,
                @Field("refal_amt") String refal_amt
        );


        @POST("booking_api.php")
        @FormUrlEncoded
        Call<BookingUploadModel> getBookingUploadModule(
                @Field("emp") String emp
        );


        @POST("booking_edit.php")
        @Multipart
        Call<BookingSubmitModel> getSubmitBooking(
                @Part("id") RequestBody id,
                @Part("bno") RequestBody bno,
                @Part("date") RequestBody date,
                @Part("emp") RequestBody emp,
                @Part("fname") RequestBody fname,
                @Part("sname") RequestBody sname,
                @Part("mobileno") RequestBody mobileno,
                @Part("whno") RequestBody whno,
                @Part("ref_name") RequestBody ref_name,
                @Part("ref_no") RequestBody ref_no,
                @Part("state") RequestBody state,
                @Part("city") RequestBody city,
                @Part("distric") RequestBody distric,
                @Part("tehsill") RequestBody tehsill,
                @Part("village") RequestBody village,
                @Part("emp_id") RequestBody emp_id,
                @Part("type") RequestBody type,
                @Part("b_p_photo") RequestBody b_p_photo,
                @Part("check_photo") RequestBody check_photo,
                @Part("product") RequestBody product,
                @Part("pmodel") RequestBody pmodel,
                @Part("pdesc") RequestBody pdesc,
                @Part("deal_price") RequestBody deal_price,
                @Part("deal_price_in_word") RequestBody deal_price_in_word,
                @Part("gst") RequestBody gst,
                @Part("rto") RequestBody rto,
                @Part("rto_tax") RequestBody rto_tax,
                @Part("rto_passing") RequestBody rto_passing,
                @Part("insurance") RequestBody insurance,
                @Part("agent_fee") RequestBody agent_fee,
                @Part("number_plat") RequestBody number_plat,
                @Part("loan_charge") RequestBody loan_charge,
                @Part("booking_amt") RequestBody booking_amt,
                @Part("amount") RequestBody amount,
                @Part("check_neft_rtgs") RequestBody check_neft_rtgs,
                @Part("check_date") RequestBody check_date,
                @Part("check_amt") RequestBody check_amt,
                @Part("neft_rtgs_date") RequestBody neft_rtgs_date,
                @Part("neft_rtgs_amt") RequestBody neft_rtgs_amt,
                @Part("make") RequestBody make,
                @Part("model") RequestBody model,
                @Part("year") RequestBody year,
                @Part("old_amt") RequestBody old_amt,
                @Part("paper_expence") RequestBody paper_expence,
                @Part("c_amount") RequestBody c_amount,
                @Part("noc") RequestBody noc,
                @Part("hood") RequestBody hood,
                @Part("toplink") RequestBody toplink,
                @Part("drowbar") RequestBody drowbar,
                @Part("toolkit") RequestBody toolkit,
                @Part("bumper") RequestBody bumper,
                @Part("hitech") RequestBody hitech,
                @Part("desc") RequestBody desc,
                @Part("atype") RequestBody atype,
                @Part("skim") RequestBody skim,
                @Part MultipartBody.Part image1,
                @Part MultipartBody.Part image2,
                @Part MultipartBody.Part image3,
                @Part MultipartBody.Part image6,
                @Part MultipartBody.Part image7,
                @Part MultipartBody.Part image8,
                @Part MultipartBody.Part image9,
                @Part MultipartBody.Part image10,
                @Part MultipartBody.Part image11,
                @Part MultipartBody.Part image12,
                @Part MultipartBody.Part imgg1,
                @Part MultipartBody.Part imgg2,
                @Part MultipartBody.Part imgg3,
                @Part MultipartBody.Part imgg4,
                @Part MultipartBody.Part imgg5
        );


        @POST("loan_api.php")
        @FormUrlEncoded
        Call<LoanDataDisplayModel> getLoanDataDisplay(
                @Field("emp") String emp
        );


        @GET("b_finance.php")
        Call<FinanceFormModel> getFinanceForm();


        @POST("b_loan.php")
        @Multipart
        Call<LoanSubmitBookingModel> getLoanSubmit(
                @Part("bno") RequestBody bno,
                @Part("date") RequestBody date,
                @Part("emp") RequestBody emp,
                @Part("fname") RequestBody fname,
                @Part("sname") RequestBody sname,
                @Part("mobileno") RequestBody mobileno,
                @Part("whno") RequestBody whno,
                @Part("ref_name") RequestBody ref_name,
                @Part("ref_no") RequestBody ref_no,
                @Part("state") RequestBody state,
                @Part("city") RequestBody city,
                @Part("distric") RequestBody distric,
                @Part("tehsill") RequestBody tehsill,
                @Part("village") RequestBody village,
                @Part("emp_id") RequestBody emp_id,
                @Part("type") RequestBody type,
                @Part("b_p_photo") RequestBody b_p_photo,
                @Part("check_photo") RequestBody check_photo,
                @Part("product") RequestBody product,
                @Part("pmodel") RequestBody pmodel,
                @Part("pdesc") RequestBody pdesc,
                @Part("deal_price") RequestBody deal_price,
                @Part("deal_price_in_word") RequestBody deal_price_in_word,
                @Part("gst") RequestBody gst,
                @Part("rto") RequestBody rto,
                @Part("rto_tax") RequestBody rto_tax,
                @Part("rto_passing") RequestBody rto_passing,
                @Part("insurance") RequestBody insurance,
                @Part("agent_fee") RequestBody agent_fee,
                @Part("number_plat") RequestBody number_plat,
                @Part("loan_charge") RequestBody loan_charge,
                @Part("booking_amt") RequestBody booking_amt,
                @Part("amount") RequestBody amount,
                @Part("check_neft_rtgs") RequestBody check_neft_rtgs,
                @Part("check_date") RequestBody check_date,
                @Part("check_amt") RequestBody check_amt,
                @Part("neft_rtgs_date") RequestBody neft_rtgs_date,
                @Part("neft_rtgs_amt") RequestBody neft_rtgs_amt,
                @Part("make") RequestBody make,
                @Part("model") RequestBody model,
                @Part("year") RequestBody year,
                @Part("old_amt") RequestBody old_amt,
                @Part("paper_expence") RequestBody paper_expence,
                @Part("c_amount") RequestBody c_amount,
                @Part("noc") RequestBody noc,
                @Part("hood") RequestBody hood,
                @Part("toplink") RequestBody toplink,
                @Part("drowbar") RequestBody drowbar,
                @Part("toolkit") RequestBody toolkit,
                @Part("bumper") RequestBody bumper,
                @Part("hitech") RequestBody hitech,
                @Part("desc") RequestBody desc,
                @Part MultipartBody.Part image1,
                @Part MultipartBody.Part image2,
                @Part MultipartBody.Part image3,
                @Part MultipartBody.Part image6,
                @Part MultipartBody.Part image7,
                @Part MultipartBody.Part image8,
                @Part MultipartBody.Part image9,
                @Part MultipartBody.Part image10,
                @Part MultipartBody.Part image11,
                @Part MultipartBody.Part image12,
                @Part("id") RequestBody id,
                @Part("r_e_name") RequestBody r_e_name,
                @Part("finance_from") RequestBody finance_from,
                @Part("loan_amount") RequestBody loan_amount,
                @Part("l_sec_amt") RequestBody l_sec_amt,
                @Part("lloan_charge") RequestBody lloan_charge,
                @Part("land_details") RequestBody land_details,
                @Part("cibil_score") RequestBody cibil_score,
                @Part("fi_date") RequestBody fi_date,
                @Part("sectiondate") RequestBody sectiondate,
                @Part("stage") RequestBody stage,
                @Part("atype") RequestBody atype,
                @Part("skim") RequestBody skim,
                @Part("cash_date") RequestBody cash_date,
                @Part("cash_amount") RequestBody cash_amount,
                @Part("cash_description") RequestBody cash_description,
                @Part MultipartBody.Part do_photo13,
                /*@Part MultipartBody.Part do_photo14,
                @Part MultipartBody.Part do_photo15,*/
                @Part MultipartBody.Part do_photo16,
                @Part MultipartBody.Part imgg1,
                @Part MultipartBody.Part imgg2,
                @Part MultipartBody.Part imgg3,
                @Part MultipartBody.Part imgg4,
                @Part MultipartBody.Part imgg5,
                @Part MultipartBody.Part imgg6

        );


        @POST("b_delivery.php")
        @FormUrlEncoded
        Call<DeliveryDataDisplayModel> getDeliveryDataDisplay(
                @Field("emp") String emp
        );


        @POST("delivery_add.php")
        @Multipart
        Call<DeliveryBookingModel> getDeliverySubmit(
                @Part("bno") RequestBody bno,
                @Part("date") RequestBody date,
                @Part("emp") RequestBody emp,
                @Part("fname") RequestBody fname,
                @Part("sname") RequestBody sname,
                @Part("mobileno") RequestBody mobileno,
                @Part("whno") RequestBody whno,
                @Part("ref_name") RequestBody ref_name,
                @Part("ref_no") RequestBody ref_no,
                @Part("state") RequestBody state,
                @Part("city") RequestBody city,
                @Part("distric") RequestBody distric,
                @Part("tehsill") RequestBody tehsill,
                @Part("village") RequestBody village,
                @Part("emp_id") RequestBody emp_id,
                @Part("type") RequestBody type,
                @Part("b_p_photo") RequestBody b_p_photo,
                @Part("check_photo") RequestBody check_photo,
                @Part("product") RequestBody product,
                @Part("pmodel") RequestBody pmodel,
                @Part("pdesc") RequestBody pdesc,
                @Part("deal_price") RequestBody deal_price,
                @Part("deal_price_in_word") RequestBody deal_price_in_word,
                @Part("gst") RequestBody gst,
                @Part("rto") RequestBody rto,
                @Part("rto_tax") RequestBody rto_tax,
                @Part("rto_passing") RequestBody rto_passing,
                @Part("insurance") RequestBody insurance,
                @Part("agent_fee") RequestBody agent_fee,
                @Part("number_plat") RequestBody number_plat,
                @Part("loan_charge") RequestBody loan_charge,
                @Part("booking_amt") RequestBody booking_amt,
                @Part("amount") RequestBody amount,
                @Part("check_neft_rtgs") RequestBody check_neft_rtgs,
                @Part("check_date") RequestBody check_date,
                @Part("check_amt") RequestBody check_amt,
                @Part("neft_rtgs_date") RequestBody neft_rtgs_date,
                @Part("neft_rtgs_amt") RequestBody neft_rtgs_amt,
                @Part("make") RequestBody make,
                @Part("model") RequestBody model,
                @Part("year") RequestBody year,
                @Part("old_amt") RequestBody old_amt,
                @Part("paper_expence") RequestBody paper_expence,
                @Part("c_amount") RequestBody c_amount,
                @Part("noc") RequestBody noc,
                @Part("hood") RequestBody hood,
                @Part("toplink") RequestBody toplink,
                @Part("drowbar") RequestBody drowbar,
                @Part("toolkit") RequestBody toolkit,
                @Part("bumper") RequestBody bumper,
                @Part("hitech") RequestBody hitech,
                @Part("desc") RequestBody desc,
                @Part MultipartBody.Part image1,
                @Part MultipartBody.Part image2,
                @Part MultipartBody.Part image3,
                @Part MultipartBody.Part image6,
                @Part MultipartBody.Part image7,
                @Part MultipartBody.Part image8,
                @Part MultipartBody.Part image9,
                @Part MultipartBody.Part image10,
                @Part MultipartBody.Part image11,
                @Part MultipartBody.Part image12,
                @Part("id") RequestBody id,
                @Part("r_e_name") RequestBody r_e_name,
                @Part("finance_from") RequestBody finance_from,
                @Part("loan_amount") RequestBody loan_amount,
                @Part("l_sec_amt") RequestBody l_sec_amt,
                @Part("lloan_charge") RequestBody lloan_charge,
                @Part("land_details") RequestBody land_details,
                @Part("cibil_score") RequestBody cibil_score,
                @Part("fi_date") RequestBody fi_date,
                @Part("sectiondate") RequestBody sectiondate,
                @Part("stage") RequestBody stage,
                @Part MultipartBody.Part do_photo13,
               /* @Part MultipartBody.Part do_photo14,
                @Part MultipartBody.Part do_photo15,*/
                @Part MultipartBody.Part do_photo16,
                @Part MultipartBody.Part do_photo17,
                @Part MultipartBody.Part do_photo18,
                @Part("delivery_date") RequestBody delivery_date,
                @Part("tyre") RequestBody tyre,
                @Part("battery") RequestBody battery,
                @Part("atype") RequestBody atype,
                @Part("skim") RequestBody skim,
                @Part("cash_date") RequestBody cash_date,
                @Part("cash_amount") RequestBody cash_amount,
                @Part("cash_description") RequestBody cash_description,
                @Part MultipartBody.Part imgg1,
                @Part MultipartBody.Part imgg2,
                @Part MultipartBody.Part imgg3,
                @Part MultipartBody.Part imgg4,
                @Part MultipartBody.Part imgg5,
                @Part MultipartBody.Part imgg6,
                @Part("clear") RequestBody clear,
                @Part("num_plate_order") RequestBody num_plate_order,
                @Part("num_plate_recive") RequestBody num_plate_recive,
                @Part("rc_book_financial") RequestBody rc_book_financial,
                @Part("register_no") RequestBody register_no

        );


        @POST("docprint.php")
        @FormUrlEncoded
        Call<DocumentPrintDataModel> getDocPrint(
                @Field("emp") String emp
        );


        @POST("passing_api.php")
        @FormUrlEncoded
        Call<PassingDataModel> getPassingData(
                @Field("emp") String emp
        );


        @POST("paypen.php")
        @FormUrlEncoded
        Call<PaymentPendingModel> getPaymentPending(
                @Field("emp") String emp
        );


        /*paypen2.php*/
        @POST("paypen2.php")
        @FormUrlEncoded
        Call<PaymentPendingModel> getWalletPaymentPending(
                @Field("emp") String emp
        );

        @POST("marketing_performance.php")
        @FormUrlEncoded
        Call<PaymentPendingModel> getMarketPaymentPending(
                @Field("emp") String emp
        );


        @POST("paypen.php")
        @FormUrlEncoded
        Call<PaymentPendingModel> getPaymentPending1(
                @Field("emp") String emp,
                @Field("cat_id") String cat_id,
                @Field("village_id") String village_id

        );


        @POST("paymentpendingapi.php")
        @FormUrlEncoded
        Call<Payment_PayPenModel> getPayment(
                @Field("id") String id,
                @Field("emp") String emp,
                @Field("mobile") String mobile
        );


        @POST("booking_count.php")
        @FormUrlEncoded
        Call<BookingCountModel> getBookingCount(
                @Field("emp") String emp
        );


        @POST("booking_clear.php")
        @FormUrlEncoded
        Call<ClearPayPenData> getClearPayPenData(
                @Field("id") String id,
                @Field("model_name") String model_name,
                @Field("product") String product,
                @Field("empid") String empid,
                @Field("mobileno") String mobileno,
                @Field("cuname") String cuname,
                @Field("village") String village,
                @Field("ddate") String ddate
        );


        //-----------insentive

        @POST("insentive_list.php")
        @FormUrlEncoded
        Call<InsentiveWalletModel> getInsensitiveData(
                @Field("emp_id") String emp_id
        );

        @POST("insentive_delivery.php")
        @FormUrlEncoded
        Call<InsentiveWalletModel> getInsensitiveDeliveryData(
                @Field("emp_id") String emp_id
        );


        @POST("withdrawal.php")
        @FormUrlEncoded
        Call<WithdrawalWalletModel> getWithDraw(
                @Field("id") String id,
                @Field("empid") String empid

        );


        @POST("wpart_list.php")
        @FormUrlEncoded
        Call<WorkShopAddProductModel> getWorkshopAddProd(
                @Field("Product") String Product
        );


        @POST("part_data.php")
        @FormUrlEncoded
        Call<PartDataModel> getPartData(
                @Field("modelid") String modelid
        );


        @GET("wemp_list.php")
        Call<WsEmpListModel> getWsEmpList();

        @POST("wqty_check.php")
        @FormUrlEncoded
        Call<QtyAvailableWSModel> getQtyAvailable(
                @Field("modelid") String modelid,
                @Field("qty") String qty
        );


        @POST("wsell_first.php")
        @FormUrlEncoded
        Call<PhaseOneWsAdd> getPhaseOneWS(
                @Field("comno") String comno,
                @Field("type") String type,
                @Field("p_type") String p_type,
                @Field("mid") String mid,
                @Field("model_name") String model_name,
                @Field("mno") String mno,
                @Field("qty") String qty,
                @Field("rate") String rate,
                @Field("totalprice") String totalprice,
                @Field("status") String status,
                @Field("works_ser") String works_ser
        );


        @POST("wsell_sec.php")
        @FormUrlEncoded
        Call<SecondPhaseAddWsModel> getSecondPhaseWs(
                @Field("id") String id,
                @Field("s_emp") String s_emp,
                @Field("l_emp") String l_emp,
                @Field("laber_charge") String laber_charge,
                @Field("petrol_charge") String petrol_charge,
//             @Field("works_ser")String works_ser,
                @Field("status") String status
        );


        @POST("workshop_check.php")
        @FormUrlEncoded
        Call<WorkShoPhaseModel> getWorkShopPhase(
                @Field("id") String id
        );

        @POST("wsell_third.php")
        @FormUrlEncoded
        Call<ThirdWSModel> getThirdWs(
                @Field("id") String id,
                @Field("deal_price") String deal_price,
                @Field("deal_price_word") String deal_price_word,
                @Field("gst") String gst,
                @Field("status") String status
        );


        @POST("wsell_four.php")
        @FormUrlEncoded
        Call<FourthWSModel> getFourthPhaseWs(
                @Field("id") String id,
                @Field("s_date") String s_date,
                @Field("cname") String cname,
                @Field("mobileno") String mobileno,
                @Field("whno") String whno,
                @Field("state") String state,
                @Field("city") String city,
                @Field("distric") String distric,
                @Field("tehsil") String tehsil,
                @Field("village") String village,
                @Field("emp_id") String emp_id,
                @Field("payment_type") String payment_type,

                @Field("cash_date") String cash_date,
                @Field("cash_amt") String cash_amt,
                @Field("cash_desc") String cash_desc,
                @Field("b_type") String b_type,
                @Field("check_date") String check_date,
                @Field("check_amt") String check_amt,
                @Field("check_desc") String check_desc,
                @Field("neft_rtgs_date") String neft_rtgs_date,
                @Field("neft_rtgs_amt") String neft_rtgs_amt,
                @Field("neft_rtgs_desc") String neft_rtgs_desc,

                @Field("left_amt") String left_amt,
                @Field("left_status") String left_status,
                @Field("vilage_wise_emp") String vilage_wise_emp,

                @Field("status") String status
        );


        @POST("wpaypen.php")
        @FormUrlEncoded
        Call<WsPaymentPendingModel> getWsPaymentPending(
                @Field("emp") String emp
        );


        @POST("workshop_ladger.php")
        @FormUrlEncoded
        Call<PaymentWSModel> getPaymentWs(
                @Field("id") String id,
                @Field("emp") String emp
        );


        @POST("borrow_list_count.php")
              @FormUrlEncoded
        Call<PaymentCollectionBorrowListCount> getPCBorrowListCount(
                 @Field("type") String type
        );


        @GET("boroowone.php")
        Call<BorrowOneModel> getBorrowOne();


        @GET("boroowtwo.php")
        Call<BorrowTwoModel> getBorrowTwo();


        @POST("borrow_ladger_one.php")
        @FormUrlEncoded
        Call<BorrowOneLedgerModel> getBorrowLedgerOne(
                @Field("id") String id,
                @Field("mobile") String mobile
        );

        //
        @POST("borrow_ladger_two.php")
        @FormUrlEncoded
        Call<BorrowLedgerTwoModel> getBorrowLedgerTwo(
                @Field("id") String id,
                @Field("mobile") String mobile
        );


        @GET("cat_list_inq.php")
        Call<InquiryDataBankModel> getInquiryData(
        );


        @POST("inq_gn.php")
        @FormUrlEncoded
        Call<InquiryGeneralVisitMainModel> getInquiryGeneralMain(
                @Field("emp") String emp
        );

        @POST("deal_stage_cat_list.php")
        @FormUrlEncoded
        Call<InquiryGeneralVisitMainModel> getInquiryGeneralMain_dealstage(
                @Field("emp") String emp,
                @Field("login_id") String login_id
        );


        @POST("inq_detail.php")
        @FormUrlEncoded
        Call<inquiryGenDetailsModel> getGenInquiryDetail(
                @Field("cat_id") String cat_id,
                @Field("emp") String emp
        );

        @POST("booking_number_get.php")
        @FormUrlEncoded
        Call<inquiryGenDetailsModel> getBookingNumber(
                @Field("cat_id") String cat_id
        );


        @POST("inq_add.php")
        @FormUrlEncoded
        Call<InqAddGenVisitModel> getInqAddGenVisit(
                @Field("login_id") String login_id,
                @Field("cat_id") String cat_id,
                @Field("sname") String sname,
                @Field("reason") String reason,
                @Field("booking") String booking,
                @Field("booking_amt") String booking_amt,
                @Field("delivry") String delivry,
                @Field("model_name") String model_name,
                @Field("vdate") String vdate,
                @Field("sell_lost") String sell_lost,
                @Field("sell_model") String sell_model,
                @Field("inq_type") String inq_type,
                @Field("vemp") String vemp,
                @Field("follow_up_type") String follow_up_type,
                @Field("drop_inq") String drop_inq
        );


        @POST("inq_monthly.php")
        @FormUrlEncoded
        Call<InqMonthlyModel> getMonthlyInq(
                @Field("emp") String emp
        );


        @POST("inq_weekly.php")
        @FormUrlEncoded
        Call<WeeklyInquiryOneModel> getWeeklyInqOne(
                @Field("emp") String emp
        );


        @POST("inq_daliy.php")
        @FormUrlEncoded
        Call<DailyInqOneModel> getDailyInqOne(
                @Field("emp") String emp
        );


        @POST("inq_full_detail.php")
        @FormUrlEncoded
        Call<InquiryAllDatWeekDayMonthModel> getInquiryAllData(
                @Field("id") String id,
                @Field("emp") String emp
        );


        @POST("inq_v_detail.php")
        @FormUrlEncoded
        Call<VisitInquiryModel> getVisitInquiry(
                @Field("emp") String emp,
                @Field("sname") String sname
        );


        @POST("inq_add_new.php")
        @FormUrlEncoded
        Call<AddInquiryModel> getAddInquiry(
                @Field("login_id") String login_id,
                @Field("cat_id") String cat_id,
                @Field("sname") String sname,
                @Field("reason") String reason,
                @Field("booking") String booking,
                @Field("booking_amt") String booking_amt,
                @Field("delivry") String delivry,
                @Field("model_name") String model_name,
                @Field("payment_collection") String payment_collection,
                @Field("amount") String amount,
                @Field("vdate") String vdate,
                @Field("vdays") String vdays,
                @Field("addgcust") String addgcust,
                @Field("sell_lost") String sell_lost,
                @Field("sell_model") String sell_model,
                @Field("inq_type") String inq_type,
                @Field("vemp") String vemp,
                @Field("follow_up_type") String follow_up_type,
                @Field("drop_inq") String drop_inq
        );


        @POST("overtime_display.php")
        @FormUrlEncoded
        Call<OverTimeWalletModel> getOverTimeWallet(
                @Field("emp_id") String emp_id,
                @Field("fdate") String fdate,
                @Field("tdate") String tdate
        );


        @POST("check_ot_phase.php")
        @FormUrlEncoded
        Call<CheckOtPhaseModel> getCheckOtPhase(
                @Field("id") String id
        );


        @POST("travaling_start.php")
        @Multipart
        Call<StartTravellingModel> getStartTravelling(
                @Part("emp") RequestBody emp,
                @Part("ch_km") RequestBody ch_km,
                @Part MultipartBody.Part image8
        );


        @POST("travaling_end.php")
        @Multipart
        Call<EndTravelingModel> getEndTraveling(
                @Part("id") RequestBody id,
                @Part("chout_km") RequestBody chout_km,
                @Part MultipartBody.Part image3
        );


        @POST("travaling_phase.php")
        @FormUrlEncoded
        Call<PhaseTravelingModel> getPhaseTraveling(
                @Field("id") String id
        );


        @POST("travaling_display.php")
        @FormUrlEncoded
        Call<TravelingDataDisplayModel> getTravelingData(
                @Field("emp_id") String emp_id,
                @Field("fdate") String fdate,
                @Field("tdate") String tdate
        );


        @POST("customer_profile_add.php")
        @FormUrlEncoded
        Call<AddCustomerModel> getAddCustomerProfile(
                @Field("mobile_no") String mobile_no,
                @Field("login_id") String login_id,
                @Field("cat_id") String cat_id,
                @Field("f_name") String f_name,
                @Field("l_name") String l_name,
                @Field("state") String state,
                @Field("city") String city,
                @Field("distric") String distric,
                @Field("tehsil") String tehsil,
                @Field("vilage") String vilage,
                @Field("mobile") String mobile,
                @Field("whatsappno") String whatsappno,
                @Field("emp") String emp,
                @Field("desc") String desc,
                @Field("auto_id") String auto_id
        );


        @POST("cudetail_fatch.php")
        @FormUrlEncoded
        Call<MobileDataAddCustomerProfileModel> getMobileDataAddCustomer(
                @Field("mobile") String mobile
        );


        @POST("customer_profile_view.php")
        @FormUrlEncoded
        Call<ViewCustomerProfileDataModel> getViewProfileDetail(
                @Field("mobile") String mobile
        );


        @GET("view_inq_emp.php")
        Call<ViewInquiryEmployeeListModel> getEmployeeList();


        /*wakking_entry_update.php*/

        @POST("wakking_entry_update.php")
        @FormUrlEncoded
        Call<ComplainBoxSubModel> getWalkingEntryList   (
                @Field("emp_id") String emp_id,
                @Field("w_reason") String w_reason,
                @Field("reason_date") String reason_date,
                @Field("maker") String maker,
                @Field("model_name") String model_name,
                @Field("registration_no") String registration_no,
                @Field("manufacture_year") String manufacture_year,
                @Field("cat_id") String cat_id,
                @Field("cid") String cid
        );

        @POST("new_reason_entry_update.php")
        @FormUrlEncoded
        Call<ComplainBoxSubModel> getNewVisitEntryList   (
                @Field("emp_id") String emp_id,
                @Field("new_reason") String w_reason,
                @Field("new_reason_date") String reason_date,
                @Field("maker") String maker,
                @Field("model_name") String model_name,
                @Field("registration_no") String registration_no,
                @Field("manufacture_year") String manufacture_year,
                @Field("cat_id") String cat_id,
                @Field("cid") String cid
        );

        @POST("activity_reason_entry_update.php")
        @FormUrlEncoded
        Call<ComplainBoxSubModel> getActivityEntryList   (
                @Field("emp_id") String emp_id,
                @Field("activity_reason") String w_reason,
                @Field("activity_reason_date") String reason_date,
                @Field("maker") String maker,
                @Field("model_name") String model_name,
                @Field("registration_no") String registration_no,
                @Field("manufacture_year") String manufacture_year,
                @Field("cat_id") String cat_id,
                @Field("cid") String cid
        );

        @POST("view_inq_emp_per.php")
        @FormUrlEncoded
        Call<ViewInqUserGenListCateModel> getViewUserInqGenList(
                @Field("user_id") String emp
        );

        @POST("view_inq_gn.php")
        @FormUrlEncoded
        Call<ViewInqGenListCateModel> getViewInqGenList(
                @Field("emp") String emp
        );


        @POST("view_inq_gn_cat_detail.php")
        @FormUrlEncoded
        Call<GeneralCatDataViewInqModel> getGenCatDataVI(
                @Field("cat_id") String cat_id,
                @Field("emp") String emp
        );


        @POST("view_inq_gn_add.php")
        @FormUrlEncoded
        Call<ViewInqFormAddModel> getViewInqAdd(
                @Field("login_id") String login_id,
                @Field("cat_id") String cat_id,
                @Field("inq_type") String inq_type,
                @Field("sname") String sname,
                @Field("reason") String reason,
                @Field("booking") String booking,
                @Field("booking_amt") String booking_amt,
                @Field("delivry") String delivry,
                @Field("model_name") String model_name,
                @Field("vdate") String vdate,
                @Field("sell_lost") String sell_lost,
                @Field("sell_model") String sell_model,
                @Field("vemp") String vemp,
                @Field("follow_up_type") String follow_up_type,
                @Field("drop_inq") String drop_inq
        );


        @POST("monthly_view_inq.php")
        @FormUrlEncoded
        Call<ViewInqMonthlyOneModel> getViewInqMonth(
                @Field("emp") String emp
        );


        @POST("inq_view_full_detail.php")
        @FormUrlEncoded
        Call<MonthlyDataViewInqModel> getMonthDataDetail(
                @Field("id") String id,
                @Field("emp") String emp
        );


        @POST("weekly_view_inq.php")
        @FormUrlEncoded
        Call<WeeklyViewInqModel> getWeeklyViewInq(
                @Field("emp") String emp
        );


        @POST("daliy_view_inq.php")
        @FormUrlEncoded
        Call<DailyViewInqModel> getDailyViewInq(
                @Field("emp") String emp
        );


        @POST("view_inq_un_add.php")
        @FormUrlEncoded
        Call<DailyMonthWeekVIModel> getDailyWeekMonthVI(
                @Field("login_id") String login_id,
                @Field("cat_id") String cat_id,
                @Field("inq_type") String inq_type,
                @Field("sname") String sname,
                @Field("reason") String reason,
                @Field("booking") String booking,
                @Field("booking_amt") String booking_amt,
                @Field("delivry") String delivry,
                @Field("model_name") String model_name,
                @Field("vdate") String vdate,
                @Field("sell_lost") String sell_lost,
                @Field("sell_model") String sell_model,
                @Field("vemp") String vemp,
                @Field("follow_up_type") String follow_up_type,
                @Field("drop_inq") String drop_inq
        );


        @GET("workshop_manager_list.php")
        Call<WsManagerDataDisplayModel> getWsManagerDetails();


        @POST("workshop_manager_add.php")
        @Multipart
        Call<WSManagerEditModel> getWsManagerEdit(
                @Part("mobile_no") RequestBody mobile_no,
                @Part("part_id") RequestBody part_id,
                @Part("login_id") RequestBody login_id,
                @Part("cu_profile") RequestBody cu_profile,
                @Part("profile_desc") RequestBody profile_desc,
                @Part("remark") RequestBody remark,
                @Part("remark_val") RequestBody remark_val,
                @Part("next_date") RequestBody next_date,
                @Part("engine_no") RequestBody engine_no,
                @Part("chasis_no") RequestBody chasis_no,
                @Part MultipartBody.Part image1,
                @Part MultipartBody.Part image2
                //@Part MultipartBody.Part image3
        );


        @POST("workshop_managar_invoice.php")
        @FormUrlEncoded
        Call<InvoiceDataDisplayModel> getInvoiceData(
                @Field("fdate") String fdate,
                @Field("tdate") String tdate
        );


        @POST("workshop_invoice_view.php")
        @FormUrlEncoded
        Call<InvoiceViewModel> getInvoiceView(
                @Field("id") String id
        );


        @GET("workshop_genral_list.php")
        Call<WSGeneralModel> getGeneralWS();


        @GET("workshop_monthly.php")
        Call<MonthlyOneWorkShopModel> getMonthlyWSOne();


        @GET("workshop_weekly.php")
        Call<WeekOneWsModel> getWeekWsOne();


        @GET("workshop_daliy.php")
        Call<DailyWsOneModel> getDailyWSOne();


        @POST("workshop_full_detail.php")
        @FormUrlEncoded
        Call<WsMonthWeekDailyModel> getWsMonthWeekDay(
                @Field("id") String id
        );


        @POST("workshop_mwd_add.php")
        @FormUrlEncoded
        Call<WSMonthWeekDailyEditFormModel> getWsMonthWeekDayFormEdit(
                @Field("login_id") String login_id,
                @Field("mobile_no") String mobile_no,
                @Field("remark") String remark,
                @Field("remark_val") String remark_val,
                @Field("next_date") String next_date,
                @Field("pid") String pid
        );

        @POST("delete_databank_list.php")
        @FormUrlEncoded
        Call<ChackinModel> deleteRecord(
                @Field("emp") String emp ,
                @Field("id") String id
        );


        @GET("feedback_call.php")
        Call<FeedbackCallWSModel> getFeedbackWs();


        @GET("workshop_display_number.php")
        Call<WorkshopNumberAllModel> getWorkshopDIsplayNumber();


        @POST("remove_call_workshop.php")
        @FormUrlEncoded
        Call<WorkshopFeedbackCallremoveModel> getRemoveCallWs(
                @Field("emp_id") String emp_id,
                @Field("call_id") String call_id,
                @Field("mnumber") String mnumber
        );


        @POST("remove_call.php")
        @FormUrlEncoded
        Call<DeleteCallWorkshopModel> getDeleteCallWs(
                @Field("emp_id") String emp_id,
                @Field("call_id") String call_id,
                @Field("mnumber") String mnumber,
                @Field("remove_remark") String remove_remark
        );

        @POST("end_task.php")
        @FormUrlEncoded
        Call<EndTaskModel> getEndTask(
                @Field("emp") String emp
        );


        @POST("view_inq_home.php")
        @FormUrlEncoded
        Call<VisitVIModel> getVisitVi(
                @Field("sname") String sname

        );


        @POST("inser_payment_collection.php")
        @FormUrlEncoded
        Call<insertPaymentCollectionModel> getInsertPaymentCollection(
                @Field("f_name") String f_name,
                @Field("state") String state,
                @Field("city") String city,
                @Field("distric") String distric,
                @Field("tehsil") String tehsil,
                @Field("vilage") String vilage,
                @Field("mobile") String mobile,
                @Field("whatsappno") String whatsappno,
                @Field("emp") String emp

        );


        @POST("payment_cel_clear.php")
        @FormUrlEncoded
        Call<PaymentCelClearModel> getPaymentCelClear(
                @Field("id") String id
        );


        @POST("cat_per_detail.php")
        @FormUrlEncoded
        Call<CatPerDetailModel> getCatPerDetails(
                @Field("id") String id
        );


        @POST("cu_profile_edit_view.php")
        @FormUrlEncoded
        Call<ViewCustomerProfileEditModel> getViewCuProfileEdit(
                @Field("mobile") String mobile
        );


        @POST("cu_profile_edit.php")
        @Multipart
        Call<EditProfileModel> getCuEditProfile(
                @Part("id") RequestBody id,
                @Part("fname") RequestBody fname,
                @Part("lname") RequestBody lname,
                @Part("state") RequestBody state,
                @Part("city") RequestBody city,
                @Part("distric") RequestBody distric,
                @Part("tehsill") RequestBody tehsill,
                @Part("village") RequestBody village,
                @Part("mobileno") RequestBody mobileno,
                @Part("whno") RequestBody whno,
                @Part("tractor") RequestBody tractor,
                @Part("model_t_name") RequestBody model_t_name,
                @Part("mfgy") RequestBody mfgy,
                @Part("engineno") RequestBody engineno,
                @Part("chasisno") RequestBody chasisno,
                @Part("rotavater") RequestBody rotavater,
                @Part("speeddrel") RequestBody speeddrel,
                @Part("pelough") RequestBody pelough,
                @Part("catid") RequestBody catid,
                @Part("emp") RequestBody emp,
                @Part("cu_status") RequestBody cu_status,
                @Part("elogin_id") RequestBody elogin_id,
                @Part("description") RequestBody description,
                @Part("mobile_no") RequestBody mobile_no,
                @Part("maker") RequestBody maker,
                @Part("model_name") RequestBody model_name,
                @Part("registration_no") RequestBody registration_no,
                @Part("thrashor") RequestBody thrashor,
                @Part("trailers") RequestBody trailers,
                @Part("cultivator") RequestBody cultivator,
                @Part("lavlor") RequestBody lavlor,
                @Part("payment_type") RequestBody payment_type,
                @Part("passing_type") RequestBody passing_type,


                @Part MultipartBody.Part image1,
                @Part MultipartBody.Part image2
        );


        @GET("edit_cuprofile_category.php")
        Call<CUEditProfileEmpListModel> getEditCuProfileCat();


      /*  @POST("my_profile_cat.php")
        @FormUrlEncoded
        Call<MyProfileCategoryModel> getMyProfileCategory(
                @Field("emp") String emp
        );*/

        @GET("my_profile_cat.php")
        Call<MyProfileCategoryModel> getMyProfileCategory(
                @Query("emp") String emp
        );



        @POST("first_meeting_histroy.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> getFirstMeetingReviewHistory(
                @Field("emp") String emp
        );


        @POST("inq_type_histroy.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> getInqTypeReviewHistory(
                @Field("emp") String emp,
                @Field("inq_type") String inq_type

        );

        @POST("review_histroy.php")
        @FormUrlEncoded
        Call<ActivityReviewData> getActivityReview(
                @Field("emp") String emp
        );

        @POST("waking_entry_get.php")
        @FormUrlEncoded
        Call<ActivityReviewData> getWalkingActivityReview(
                @Field("emp") String emp
        );

        @POST("new_visit_entry_get.php")
        @FormUrlEncoded
        Call<ActivityReviewData> getNewVisitEntry(
                @Field("emp") String emp
        );

        @POST("selllost_inq_histroy.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> getSellLostReviewHistory(
                @Field("emp") String emp

        );

        @POST("drop_inq_histroy.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> getDropReviewHistory(
                @Field("emp") String emp

        );


        @POST("delivery_histroy.php")
        @FormUrlEncoded
        Call<DeliveryDataDisplayModel> getDeliveryHistory(
                @Field("emp") String emp

        );

        @POST("service_complain.php")
        @FormUrlEncoded
        Call<FeedbackCallWSModel> getservicecompalinReview(
                @Field("emp") String emp

        );


        @POST("booking_fitment_final.php")
        @FormUrlEncoded
        Call<RcUpdate> getbookingfitmentReview(
                @Field("emp") String emp

        );

        @POST("my_profile_cat_detail.php")
        @FormUrlEncoded
        Call<ViewProfileCategoryDetailsModel> getViewProfileDetails(
                @Field("cat_id") String cat_id,
                @Field("emp") String emp
        );


        @POST("scoreboard_list.php")
        @FormUrlEncoded
        Call<ScoreBoardDisplayModel> getScoreBoardDisplay(
                @Field("emp_id") String emp_id,
                @Field("fdate") String fdate,
                @Field("tdate") String tdate
        );


        @POST("customer_profile_csw.php")
        @FormUrlEncoded
        Call<CustomerProfileCSW_Model> getCustomerProfile_CSW(
                @Field("emp_id") String emp_id,
                @Field("call_id") String call_id,
                @Field("mnumber") String mnumber,
                @Field("type") String type
        );


        @POST("my_inq_csw.php")
        @FormUrlEncoded
        Call<myInqCswModel> getMyInqProfile_CSW(
                @Field("emp_id") String emp_id,
                @Field("call_id") String call_id,
                @Field("mnumber") String mnumber,
                @Field("type") String type
        );
        @POST("my_inq_csw.php")
        @FormUrlEncoded
        Call<myInqCswModel> getMyInqProfile_CSW(
                @Field("emp_id") String emp_id,
                @Field("call_id") String call_id,
                @Field("mnumber") String mnumber,
                @Field("type") String type,
                @Field("not_attend") String not_attend
        );


        @POST("my_profile_csw.php")
        @FormUrlEncoded
        Call<MyProfileCSWModel> getMyProfile_CSW(
                @Field("emp_id") String emp_id,
                @Field("call_id") String call_id,
                @Field("mnumber") String mnumber,
                @Field("type") String type
        );


        @POST("paypending_bd_csw.php")
        @FormUrlEncoded
        Call<BookingUpload_PaymentPendingCSW_Model> getPayPenBookingUpload(
                @Field("emp_id") String emp_id,
                @Field("call_id") String call_id,
                @Field("mnumber") String mnumber,
                @Field("type") String type
        );

        @POST("pc_csw.php")
        @FormUrlEncoded
        Call<PaymentCollection_CSWModel> getPaymentCollectionCSW(
                @Field("emp_id") String emp_id,
                @Field("call_id") String call_id,
                @Field("mnumber") String mnumber,
                @Field("type") String type
        );


        @GET("true_val_brand.php")
        Call<TrueValueBrandModel> getTrueValueBrand();


        @POST("true_val_model.php")
        @FormUrlEncoded
        Call<TrueValueBrandModel_Model> getTrueValBrandModel(
                @Field("brand_id") String brand_id
        );


        @POST("true_val_caheck_add_one.php")
        @FormUrlEncoded
        Call<TrueValueFormOneModel> getTrueValCheckOneForm(
                @Field("serch_no") String serch_no,
                @Field("cu_id") String cu_id,
                @Field("login_id") String login_id,
                @Field("check_phase") String check_phase
        );



        /**/



        /*@POST("true_val_caheck_add_two.php")
        @FormUrlEncoded
        Call<TvAddFormTwoModel> getTVAddTwoTrueVal(
                @Field("c_model") String c_model,
                @Field("mfg_y") String mfg_y,
                @Field("c_owner") String c_owner,
                @Field("c_variants") String c_variants,
                @Field("c_staring") String c_staring,
                @Field("c_gear") String c_gear,
                @Field("c_tyre_c") String c_tyre_c,
                @Field("status") String status
        );*/


        @POST("true_val_caheck_add_two.php")
        @FormUrlEncoded
        Call<TvAddFormTwoModel> getTrueValThirdAddForm(
                @Field("id") String id,
                @Field("c_brand") String c_brand,
                @Field("c_model") String c_model,
                @Field("mfg_y") String mfg_y,
                @Field("c_owner") String c_owner,
                @Field("c_variants") String c_variants,
                @Field("c_staring") String c_staring,
                @Field("c_gear") String c_gear,
                @Field("c_tyre_c") String c_tyre_c,
                @Field("status") String status
        );


        @POST("view_pro_village_wise_emp.php")
        @FormUrlEncoded
        Call<MyProfileVillageWiseEmpModel> getMyProVillageWiseEmp(
                @Field("cat_id") String cat_id,
                @Field("emp") String emp
        );


        @POST("myprofile_add.php")
        @FormUrlEncoded
        Call<MyProfileAddFormModel> getMyProfileForm(
                @Field("login_id") String login_id,
                @Field("follow_up_type") String follow_up_type,
                @Field("cat_id") String cat_id,
                @Field("sname") String sname,
                @Field("reason") String reason,
                @Field("vdate") String vdate,
                @Field("vemp") String vemp
        );


        @POST("myprofile_monthly.php")
        @FormUrlEncoded
        Call<MyProfileMonthModel> getMyProfileMonth(
                @Field("emp") String emp
        );


        @POST("myprofile_weekly.php")
        @FormUrlEncoded
        Call<WeeklyMyProfileModel> getWeeklyMyProfile(
                @Field("emp") String emp
        );


        @POST("myprofile_daliy.php")
        @FormUrlEncoded
        Call<DayMyProfileModel> getDayMyProfile(
                @Field("emp") String emp
        );


        @POST("myprofile_detail.php")
        @FormUrlEncoded
        Call<MyProfileSecondAllDetailModel> getMyProfileSecond(
                @Field("id") String id,
                @Field("emp") String emp
        );


        @POST("myprofile_new_add.php")
        @FormUrlEncoded
        Call<MwdMyProfileModel> gwtMwdMyProfile(
                @Field("login_id") String login_id,
                @Field("follow_up_type") String follow_up_type,
                @Field("cat_id") String cat_id,
                @Field("sname") String sname,
                @Field("reason") String reason,
                @Field("vdate") String vdate,
                @Field("vemp") String vemp
        );


        @POST("viewprofile_monthly.php")
        @FormUrlEncoded
        Call<MonthViewProfileModel> getMonthViewProfile(
                @Field("emp") String emp
        );


        @POST("viewprofile_weekly.php")
        @FormUrlEncoded
        Call<WeeklyViewProfile> getWeeklyViewProfile(
                @Field("emp") String emp
        );


        @POST("viewprofile_daliy.php")
        @FormUrlEncoded
        Call<DailyViewProfileModel> getDailyViewProfile(
                @Field("emp") String emp
        );


        @POST("complain_box.php")
        @FormUrlEncoded
        Call<ComplainBoxSubModel> getComplainSubmit(
                @Field("cuid") String cuid,
                @Field("compalin") String compalin,
                @Field("maker") String maker,
                @Field("model_name") String model_name,
                @Field("registration_no") String registration_no,
                @Field("manufacture_year") String manufacture_year,
                @Field("login_id") String login_id
        );


        @POST("complain_box_display.php")
        @FormUrlEncoded
        Call<ComplainBoxDisplayModel> getComplainBoxDisplay(

                @Field("fdate") String fdate,
                @Field("tdate") String tdate

        );


        @POST("complain_box_call.php")
        @FormUrlEncoded
        Call<ComplainBoxCallModel> getComplainBoxCall(
                @Field("cid") String cid
        );


        @POST("complain_new_add.php")
        @FormUrlEncoded
        Call<CompalainFormModel> getComplainFormSubmit(
                @Field("date") String date,
                @Field("desc") String desc,
                @Field("clepen") String clepen,
                @Field("cid") String cid,
                @Field("m_name") String m_name
        );

        @POST("document_box_add.php")
        @FormUrlEncoded
        Call<DocumentBoxAddModel> getDocumentBoxAdd(
                @Field("login_id") String login_id,
                @Field("cuid") String cuid,
                @Field("document") String document,
                @Field("maker") String maker,
                @Field("model_name") String model_name,
                @Field("registration_no") String registration_no,
                @Field("manufacture_year") String manufacture_year,
                @Field("desc") String desc
        );


        @GET("document_box_list.php")
        Call<DocumentBoxDisplayModel> getDocBoxDisplay();


        @POST("document_box_unadd.php")
        @FormUrlEncoded
        Call<DocBoxAddModel> getDocBoxAdd(
                @Field("cid") String cid,
                @Field("login_id") String login_id,
                @Field("vreason") String vreason,
                @Field("date") String date,
                @Field("documnet") String documnet
        );


        @GET("left_sms.php")
        Call<RequestBody> getLeftSms();


        @POST("empborrow.php")
        @FormUrlEncoded
        Call<EmpBorrowModel> getEmpBorrow(
                @Field("empid") String empid
        );


        @GET("sup_borrow.php")
        Call<SupBorrowModel> getSupBorrow();


        @GET("sup_borrow_list.php")
        Call<SupBorrowedListModel> getSupBorrowList(
        );


        @GET("delivery_report.php")
        Call<DeliveryReportModel> getDeliveryReport();


        @POST("delivery_rep_add.php")
        @FormUrlEncoded
        Call<AddViewDeliveryReportSubmitModel> getAddViewDRSubmit(
                @Field("login_id") String login_id,
                @Field("cuid") String cuid
        );


        @POST("delivery_rep_un_add.php")
        @Multipart
        Call<DeliveryGenKListSubmitModel> getDRGenListSubmit(
                @Part("id") RequestBody id,
                @Part("cuid") RequestBody cuid,
                @Part("save_mobile_no") RequestBody save_mobile_no,
                @Part("col_wh_no") RequestBody col_wh_no,
                @Part("sub_youtube_ch") RequestBody sub_youtube_ch,
                @Part("like_fb_insta_page") RequestBody like_fb_insta_page,
                @Part("toolkit_delivery") RequestBody toolkit_delivery,
                @Part("key_delivery") RequestBody key_delivery,
                @Part("check_document") RequestBody check_document,
                @Part("warranty_pollicy_discuusion") RequestBody warranty_pollicy_discuusion,
                @Part("check_oil_level") RequestBody check_oil_level,
                @Part("check_accessories") RequestBody check_accessories,
                @Part("installation") RequestBody installation,
                @Part("name_plat") RequestBody name_plat,
                @Part("tractor_number_plat") RequestBody tractor_number_plat,


                @Part("delivey_photo") RequestBody delivey_photo,
                @Part("chasis_print") RequestBody chasis_print,
                @Part("engine_no") RequestBody engine_no,
                @Part("chasis_no") RequestBody chasis_no,

                @Part("engine_no_input") RequestBody engine_no_input,
                @Part("chasis_no_input") RequestBody chasis_no_input,

                @Part("tractor_type") RequestBody tractor_type,
                @Part("model_name") RequestBody model_name,
                @Part("model_year") RequestBody model_year,
                @Part("land_recored") RequestBody land_recored,
                @Part("rent") RequestBody rent,
                @Part("rotavator") RequestBody rotavator,
                @Part("speed_dreel") RequestBody speed_dreel,
                @Part("gardan") RequestBody gardan,
                @Part("whatsapp_no") RequestBody whatsapp_no,

                @Part("smart_card") RequestBody smart_card,
                @Part("smart_card_date") RequestBody smart_card_date,

                @Part MultipartBody.Part image1,
                @Part MultipartBody.Part image2,
                @Part MultipartBody.Part image3,
                @Part MultipartBody.Part image4,
                @Part MultipartBody.Part image5,
                @Part MultipartBody.Part image6,
                @Part MultipartBody.Part image7,
                @Part MultipartBody.Part image8,
                @Part MultipartBody.Part image9,
                @Part MultipartBody.Part image10,
                @Part MultipartBody.Part image11,
                @Part MultipartBody.Part image12,
                @Part MultipartBody.Part image13,
                @Part MultipartBody.Part image14,
                @Part MultipartBody.Part image15,
                @Part MultipartBody.Part image16,
                @Part MultipartBody.Part image17,
                @Part MultipartBody.Part image18
        );


        @POST("delivert_report_display_id.php")
        @FormUrlEncoded
        Call<DelievryReportViewGenListModel> getDRGaneralListView(
                @Field("id") String id
        );


        @POST("first_servuce_add.php")
        @FormUrlEncoded
        Call<FirstServiceGLModel> getFirstServiceGL(
                @Field("login_id") String login_id,
                @Field("cuid") String cuid,
                @Field("del_id") String del_id
        );


        @GET("first_service_display.php")
        Call<FirstServiceModel> getFirstService();


        @POST("first_ser_add_new.php")
        @FormUrlEncoded
        Call<FirstServiceAddModel> getFirstServiceAdd(
                @Field("id") String id,
                @Field("mobileno") String mobileno,
                @Field("type") String type,
                @Field("follow_date") String follow_date,
                @Field("service_date") String service_date,
                @Field("mname") String mname,
                @Field("comno") String comno,
                @Field("cuid") String cuid

        );


        @GET("first_service_add_two.php")
        Call<FirstServiceAddTwoModel> getFirstServiceAddTwoModel();


        @GET("delivery_number.php")
        Call<DisplayReportCountModel> getDisplayReportCount();


        @GET("histroy_count.php")
        Call<ReviewHistoryCount> getReviewHistoryCount();


        @GET("second_ser_add.php")
        Call<SecondServiceAddTwo> getSecondServiceAdd();


        @GET("smart_card_vendor.php")
        Call<ThirdSmartCardVendorModel> getSmartCardVendor();


        @POST("smart_card_first.php")
        @FormUrlEncoded
        Call<FirstAddSmartCardModel> getFirstSmartCard(
                @Field("type") String type,
                @Field("mno_smo") String mno_smo
        );


        @POST("smart_card_sec.php")
        @FormUrlEncoded
        Call<AddPointValueSecondModel> getAddPointValueSecond(
                @Field("id") String id
        );

        @POST("smart_card_sec_add.php")
        @FormUrlEncoded
        Call<AddSmartCardSecondSubmitModel> getAddSmartCardSecond(
                @Field("login_id") String login_id,
                @Field("cuid") String cuid,
                @Field("id") String id
        );


        @POST("smart_card_vendor_otp.php")
        @Multipart
        Call<AddSmartCardThirdModel> getAddSmartCardThird(
                @Part("id") RequestBody id,
                @Part("vmobile_no") RequestBody vmobile_no,
                @Part("vid") RequestBody vid,
                @Part("saledate") RequestBody saledate,
                @Part("product") RequestBody product,
                @Part("deal_price") RequestBody deal_price,
                @Part("customer_name") RequestBody customer_name,
                @Part("village") RequestBody village,

                @Part MultipartBody.Part images
        );


        @POST("smart_card_otp_verified.php")
        @FormUrlEncoded
        Call<AddSmartCardVerfiedOtpModel> getAddSmartOTPVerified(
                @Field("id") String id,
                @Field("otp") String otp
        );


        @POST("ssp_login.php")
        @FormUrlEncoded
        Call<LoginModelSSP> getSSPLogin(
                @Field("mobile") String mobile,
                @Field("password") String password
        );


        @POST("smart_card_view_acc.php")
        @FormUrlEncoded
        Call<ViewDocVendoreModel> getViewDocVendore(
                @Field("vid") String vid,
                @Field("date1") String date1,
                @Field("date2") String date2
        );


        @POST("ssp_view_acc.php")
        @FormUrlEncoded
        Call<SSP_ViewAccountDataBankModel> getSSPViewAccDataBank(
                @Field("date1") String date1,
                @Field("date2") String date2
        );


        @GET("req_product_list.php")
        Call<RequestProductListModel> getRequsteProductAdd();


        @POST("req_vendor_list.php")
        @FormUrlEncoded
        Call<VendorAddRequestSSPMode> getVendorAddRequestSSP(
                @Field("product_name") String product_name
        );


        @POST("req_submit.php")
        @FormUrlEncoded
        Call<AddRequestSubmitSSPModel> getAddRequestSSpSubmit(
                @Field("product_name") String product_name,
                @Field("vendor") String vendor,
                @Field("mobile") String mobile,
                @Field("cuid") String cuid,
                @Field("ssp_id") String ssp_id
        );



        /*@GET("req_genral_list.php")
        Call<ReqGaneralListSSPModel> getReqGeneralListData(
        );*/

        @POST("req_genral_list.php")
        @FormUrlEncoded
        Call<ReqGaneralListSSPModel> getReqGeneralListData(
                @Field("ssp_id") String vendor
        );


        @POST("vendor_req_genral_list.php")
        @FormUrlEncoded
        Call<VendorReqGaneralModel> getVendorReqGaneralData(
                @Field("vendor") String vendor
        );


        @POST("req_cancel.php")
        @Multipart
        Call<cancel_buttion_model> get_cancel_buttion_web(
                @Part("id") RequestBody id,
                @Part("cancel_rea") RequestBody vmobile_no,

                @Part MultipartBody.Part images
        );

        @POST("req_accept.php")
        @FormUrlEncoded
        Call<Accept_butiion_model> get_accept_buttion_web(
                @Field("id") String id
        );

        @POST("req_delivery_submit.php")
        @FormUrlEncoded
        Call<ssp_delivery_buttion_model> get_ssp_delivery_buttion_web(
                @Field("id") String id
        );

        @POST("req_smart_card_insert.php")
        @Multipart
        Call<vendor_delivery_button_model> get_vendor_delivery_web(
                @Part("id") RequestBody id,
                @Part("saledate") RequestBody vmobile_no,
                @Part("deal_price") RequestBody vid,

                @Part MultipartBody.Part images
        );

        @GET("employee_work.php")
        Call<ssp_databank_employ_model> get_ssp_databank_ak_web();

        @POST("attandance_check.php")
        @FormUrlEncoded
        Call<checkornot_model_ak> chcek_or_not(
                @Field("eid") String id
        );

        @POST("check_out_check.php")
        @FormUrlEncoded
        Call<checkornot_model_ak> check_out_check_or_not(
                @Field("eid") String id
        );

        @POST("employee_tracking_insert.php")
        @FormUrlEncoded
        Call<send_location_model_ak> send_location_web_ak(
                @Field("emp") String id,
                @Field("location") String loction,
                @Field("lat") String lat,
                @Field("long") String longl,
                @Field("type") String type
        );


        @POST("emp_tracking_1st.php")
        @FormUrlEncoded
        Call<emp_tracking_mode> get_log_lang_emp_tracking(
                @Field("empid") String id
        );


        @POST("emp_tracking_2nd.php")
        @FormUrlEncoded
        Call<emp_startloc_endloc_model> get_start_end_location(
                @Field("empid") String id
        );

        @POST("emp_tracking_3rd.php")
        @FormUrlEncoded
        Call<All_data_show_tracking_model> get_filter_emp_location(
                @Field("empid") String id,
                @Field("fdate") String start,
                @Field("tdate") String end
        );

        @POST("emp_tracking_4th.php")
        @FormUrlEncoded
        Call<All_data_show_tracking_model> get_all_data_show_tracking(
                @Field("empid") String id
        );


        @POST("emp_tracking_5th.php")
        @FormUrlEncoded
        Call<ShowTripModel> ShowTripDataTracking(
                @Field("empid") String id,
                @Field("date") String date
        );

        @POST("emp_tracking_seven.php")
        @FormUrlEncoded
        Call<line_start_to_end_model> linedraw_start_to_end(
                @Field("empid") String id
        );


        @POST("req_expire.php")
        @FormUrlEncoded
        Call<ssp_zeroday_id_send_model> ssp_zeroday_send_web(
                @Field("id") String id
        );

        @POST("emp_tracking_six.php")
        @FormUrlEncoded
        Call<emp_filter_date_traking_model> emp_filterdate_traking_web(
                @Field("empid") String id,
                @Field("date") String date
        );


        @POST("room_meet_display.php")
        @FormUrlEncoded
        Call<meeting_emp_model> emp_meeting_room_web(
                @Field("type") String type,
                @Field("id") String id
        );

        @POST("cashbook_in_out.php")
        @Multipart
        Call<cashbook_inButton_model> cashbook_send_inbutton_data_web(
                @Part("login_id") RequestBody login_id,
                @Part("type") RequestBody type,
                @Part("tran_type") RequestBody tran_type,
                @Part("amount") RequestBody amount,
                @Part("c_desc") RequestBody c_desc,
                @Part("c_date") RequestBody c_date,

                @Part MultipartBody.Part image1
        );


        @GET("cashbook_display.php")
        Call<cashbook_getdata_model> cashbook_getEntry();

        @GET("get_cuurent_month_date.php")
        Call<cashbook_getDate_model> cashbook_getDate();


        @POST("cashbook_filter.php")
        @FormUrlEncoded
        Call<cashbook_dateFilter_model> cashbook_das_datefilter(
                @Field("fdate") String fdate,
                @Field("tdate") String tdate
        );

        @POST("emp_ledger_datefilter.php")
        @FormUrlEncoded
        Call<ladger_getdata_model> emp_ledger_datefilter(
                @Field("fdate") String fdate,
                @Field("tdate") String tdate,
                @Field("emp") String emp
        );

        @POST("cashbook_transfer.php")
        @FormUrlEncoded
        Call<transfer_cashtobank_model> cashbook_send_transfer_cashtobank(
                @Field("login_id") String login_id,
                @Field("c_from") String c_from,
                @Field("c_to") String c_to,
                @Field("amount") String amount,
                @Field("c_desc") String c_desc,
                @Field("c_date") String c_date,
                @Field("type") String type
        );

        @GET("cashbook_transfer_history.php")
        Call<cashbook_history_model> cashbook_getHistory();

        @GET("databank_inq_model.php")
        Call<emp_modelname_model> emp_getmodel_name_web();

        @POST("inq_remainder.php")
        @FormUrlEncoded
        Call<new_visit_model> new_visit_web(
                @Field("login_id") String login_id,
                @Field("vemp") String vemp,
                @Field("follow_up_type") String follow_up_type,
                @Field("cat_id") String cat_id,
                @Field("sname") String sname,
                @Field("reason") String reason,
                @Field("vdate") String vdate,
                @Field("location") String location
        );

        @POST("inq_add_file.php")
        @Multipart
        Call<addimage_attachfile_model> add_image_filleattach_web(
                @Part("sname") RequestBody login_id,

                @Part MultipartBody.Part image1
        );

        @POST("inq_view_file.php")
        @FormUrlEncoded
        Call<show_image_model> show_image_added_web(
                @Field("sname") String login_id
        );

        @POST("inq_deal.php")
        @Multipart
        Call<Deal_model> deal_newDesign_web(
                @Part("login_id") RequestBody login_id,
                @Part("vemp") RequestBody vemp,
                @Part("cat_id") RequestBody cat_id,
                @Part("sname") RequestBody sname,
                @Part("booking") RequestBody booking,
                @Part("booking_amt") RequestBody booking_amt,
                @Part("delivry") RequestBody delivry,
                @Part("model_name") RequestBody model_name,
                @Part("sell_lost") RequestBody sell_lost,
                @Part("sell_model") RequestBody sell_model,
                @Part("drop_inq") RequestBody drop_inq,
                @Part("drop_rea") RequestBody drop_rea,
                @Part("loan_clear") RequestBody loan_clear,
                @Part("d_p_clear") RequestBody d_p_clear,
                @Part("old_rec_book_veh") RequestBody old_rec_book_veh,

                @Part MultipartBody.Part image1,
                @Part MultipartBody.Part image2
        );

        @POST("deal_stage_not_attand.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> deal_not_attend_web(
                @Field("emp") String emp,
                @Field("cat_id") String cat_id,
                @Field("village_id") String village_id,
                @Field("model_new") String model,
                @Field("payment_type") String payment_type,
                @Field("passing_type") String passing_type,
                 @Field("deal_type") String deal_type,
                 @Field("maker") String maker,
                 @Field("model") String model1,
                 @Field("manufacture_year") String manufacture_year
        );

        @POST("update_inq.php")
        @FormUrlEncoded
        Call<Deal_model> edit_deal_stage_info(
                @Field("inq_id") String inq_id,
                @Field("sname") String sname,
                @Field("customer_name") String customer_name,
                @Field("lname") String customerl_name,
                @Field("phone") String phone,
              /*  @Field("Other_phone") String other_phone,*/
                @Field("model_name") String model_name,
                @Field("desc") String desc,
                @Field("other_mobile") String other_mobile,
                @Field("payment_type") String payment_type,
                @Field("passing_type") String passing_type,
                @Field("deal_type") String deal_type,
                @Field("maker") String maker,
                @Field("emp_id") String emp_id ,
                @Field("location") String maklocation,
//                @Field("re_note") String re_note,
                @Field("follow_up") String follow_up
        );

        @POST("recent_note_list.php")
        @FormUrlEncoded
        Call<Notes_POJO> recent_notlist(
                @Field("inq_id") String inq_id
        );

        @POST("deal_stage_type_inq.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> deal_hotInquiry_web(
                @Field("emp") String emp,
                @Field("inq_type") String inq_type,
                @Field("cat_id") String cat_id,
                @Field("village_id") String village_id,
                @Field("model_new") String model,
                @Field("payment_type") String payment_type,
                @Field("passing_type") String passing_type,
                @Field("deal_type") String deal_type,
                @Field("maker") String maker,
                @Field("model") String model1,
                @Field("manufacture_year") String manufacture_year
        );

        @POST("review_flag_update.php")
        @FormUrlEncoded
        Call<deal_stage_call_sms_what_model> getCallReview(
                @Field("phn_flag")String phn_flag,
//                @Field("whatsapp_flag")String whatsapp_flag,
//                @Field("sms_flag")String sms_flag,
//                @Field("phn_review")String phn_review,
//                @Field("whatsapp_review")String whatsapp_review,
//                @Field("sms_review")String sms_review,
//                @Field("submit_done")String submit_done,
                @Field("inq_id")String inq_id
        );

        @POST("review_flag_update.php")
        @FormUrlEncoded
        Call<deal_stage_call_sms_what_model> getSMSReview(
//                @Field("phn_flag")String phn_flag,
//                @Field("whatsapp_flag")String whatsapp_flag,
                @Field("sms_flag")String sms_flag,
//                @Field("phn_review")String phn_review,
//                @Field("whatsapp_review")String whatsapp_review,
//                @Field("sms_review")String sms_review,
//                @Field("submit_done")String submit_done,
                @Field("inq_id")String inq_id
        );

        @POST("review_flag_update.php")
        @FormUrlEncoded
        Call<deal_stage_call_sms_what_model> getWhatsappReview(
//                @Field("phn_flag")String phn_flag,
                @Field("whatsapp_flag")String whatsapp_flag,
//                @Field("sms_flag")String sms_flag,
//                @Field("phn_review")String phn_review,
//                @Field("whatsapp_review")String whatsapp_review,
//                @Field("sms_review")String sms_review,
//                @Field("submit_done")String submit_done,
                @Field("inq_id")String inq_id
        );

        @POST("not_attand_two.php")
        @FormUrlEncoded
        Call<deal_stage_call_sms_what_model> deal_send_call_sms_what_web(
                @Field("call_id") String call_id,
                @Field("stage") String stage,
                @Field("skip_call") String skip_call
        );

        /*histroy_count.php*/

        @POST("deal_stage_first_meeting.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> deal_firstMeeting_web(
                @Field("emp") String emp,
                @Field("cat_id") String cat_id,
                @Field("village_id") String village_id,
                @Field("model_new") String model ,
                @Field("payment_type") String payment_type,
                @Field("passing_type") String passing_type,
                @Field("deal_type") String deal_type,
                @Field("maker") String maker,
                @Field("model") String model1,
                @Field("manufacture_year") String manufacture_year

        );

        @POST("deal_stage_overdue.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> deal_overDue_web(
                @Field("emp") String emp,
                @Field("cat_id") String cat_id,
                @Field("village_id") String village_id,
                @Field("model_new") String model,
                @Field("payment_type") String payment_type,
                @Field("passing_type") String passing_type,
                @Field("deal_type") String deal_type,
                @Field("maker") String maker,
                @Field("model") String model1,
                @Field("manufacture_year") String manufacture_year
        );

        @POST("update_inq_customeprice.php")
        @FormUrlEncoded
        Call<Deal_model> update_dealtypedata(
                @Field("inq_id") String inq_id,
                @Field("customer_price") String customer_price,
                @Field("diffrance") String diffrance,
                @Field("market_value") String market_value,
                @Field("model_name") String model_name,
                @Field("sname") String sname,
                @Field("maker") String maker,
                @Field("registration_no") String registration_no,
                @Field("manufacture_year") String manufacture_year
        );

        @Multipart
        @POST("deal_stage_first_meeting_remainder.php")
        Call<deal_Remainder_model> deal_remainder_web1(
                @Part("login_id") RequestBody login_id,
                @Part("vemp") RequestBody vemp,
                @Part("model_new") RequestBody model_new,
                @Part("passing_type") RequestBody passing_type,
                @Part("payment_type") RequestBody payment_type,
                @Part("dealtype") RequestBody dealtype,
                @Part("make") RequestBody make,
                @Part("model") RequestBody model,
                @Part("mfgyear") RequestBody mfgyear,
                @Part("customerprice") RequestBody customerprice,
                @Part("market_value") RequestBody market_value,
                @Part("diffrence") RequestBody diffrence,
                @Part("follow_up_type") RequestBody follow_up_type,
                @Part("cat_id") RequestBody cat_id,
                @Part("sname") RequestBody sname,
                @Part("reason") RequestBody reason,
                @Part("vdate") RequestBody vdate,
                @Part("location") RequestBody location,
                @Part("mobile_no") RequestBody mobile_no,
                @Part("stage") RequestBody stage,
                @Part("negotiation") RequestBody negotiation,
                @Part("id") RequestBody id,
                @Part("date_str") RequestBody date_str,

                @Part MultipartBody.Part image

        );

        @Multipart
        @POST("deal_stage_first_meeting_remainder.php")
        Call<deal_Remainder_model> deal_remainder_web12(
                @Part("login_id") RequestBody login_id,
                @Part("vemp") RequestBody vemp,
                @Part("model_new") RequestBody model_new,
                @Part("passing_type") RequestBody passing_type,
                @Part("payment_type") RequestBody payment_type,
                @Part("dealtype") RequestBody dealtype,
                @Part("make") RequestBody make,
                @Part("model") RequestBody model,
                @Part("mfgyear") RequestBody mfgyear,
                @Part("customerprice") RequestBody customerprice,
                @Part("market_value") RequestBody market_value,
                @Part("diffrence") RequestBody diffrence,
                @Part("follow_up_type") RequestBody follow_up_type,
                @Part("cat_id") RequestBody cat_id,
                @Part("sname") RequestBody sname,
                @Part("reason") RequestBody reason,
                @Part("vdate") RequestBody vdate,
                @Part("location") RequestBody location,
                @Part("mobile_no") RequestBody mobile_no,
                @Part("stage") RequestBody stage,
                @Part("negotiation") RequestBody negotiation,
                @Part("id") RequestBody id,
                @Part("first_meeting_time") RequestBody first_meeting_time

        );


        /*insentive_approve_otp*/

        @POST("deal_stage_first_meeting_remainder.php")
        @FormUrlEncoded
        Call<deal_Remainder_model> deal_remainder_web(
                @Field("login_id") String login_id,
                @Field("vemp") String vemp,
               /* @Field("passing_type") String passing_type,
                @Field("payment_type") String payment_type,*/
                @Field("model_new") String model_new,
                @Field("follow_up_type") String follow_up_type,
                @Field("cat_id") String cat_id,
                @Field("sname") String sname,
                @Field("reason") String reason,
                @Field("vdate") String vdate,
                @Field("location") String location,
                @Field("mobile_no") String mobile_no,
                @Field("stage") String stage,
                @Field("negotiation") String negotiation,
                @Field("dealtype") String dealtype,
                @Field("make") String make,
                @Field("model") String model,
                @Field("mfgyear") String mfgyear,
                @Field("customerprice") String customerprice,
                @Field("market_value") String market_value,
                @Field("diffrence") String diffrence,
                @Field("id") String id
               /* ,@Part MultipartBody.Part image1*/
        );

        @POST("deal_stage_sec_meet_otp_verified.php")
        @FormUrlEncoded
        Call<deal_otpVerify_model> deal_otp_verify_web(
                @Field("id") String id,
                @Field("otp") String otp
        );


        @POST("insentive_approve_otp.php")
        @FormUrlEncoded
        Call<deal_otpVerify_model> insentive_otp_verify_web(
                @Field("id") String id,
                @Field("otp") String otp
        );

        @POST("deal_stage_booking.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> deal_booking_web(
                @Field("emp") String emp,
                @Field("cat_id") String cat_id,
                @Field("village_id") String village_id,
                @Field("model_new") String model,
                @Field("payment_type") String payment_type,
                @Field("passing_type") String passing_type,
                @Field("deal_type") String deal_type,
                @Field("maker") String maker,
                @Field("model") String model1,
                @Field("manufacture_year") String manufacture_year

        );

        @POST("deal_stage_delivery.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> deal_delivery_web(
                @Field("emp") String emp,
                @Field("id") String id,
                @Field("cat_id") String cat_id,
                @Field("model_new") String model,
                @Field("payment_type") String payment_type,
                @Field("passing_type") String passing_type,
                @Field("deal_type") String deal_type,
                @Field("maker") String maker,
                @Field("model") String model1,
                @Field("manufacture_year") String manufacture_year
        );

        @POST("deal_stage_selllost.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> deal_selllost_web(
                @Field("emp") String emp,
                @Field("cat_id") String cat_id,
                @Field("village_id") String village_id,
                @Field("model_new") String model,
                @Field("payment_type") String payment_type,
                @Field("passing_type") String passing_type,
                @Field("deal_type") String deal_type,
                @Field("maker") String maker,
                @Field("model") String model1,
                @Field("manufacture_year") String manufacture_year
        );

        @POST("Copy_deal_stage_type_inq.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> deal_inputmiss_web(
                @Field("empid") String emp,
                @Field("login_id") String login_id,
                @Field("cat_id") String cat_id
        );

        @POST("deal_stage_drop_inq.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> deal_dropinq_web(
                @Field("emp") String emp,
                @Field("cat_id") String cat_id,
                @Field("village_id") String village_id,
                @Field("model_new") String model,
                @Field("payment_type") String payment_type,
                @Field("passing_type") String passing_type,
                @Field("deal_type") String deal_type,
                @Field("maker") String maker,
                @Field("model") String model1,
                @Field("manufacture_year") String manufacture_year

        );


        @POST("1st_follow_up_inq.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> deal_followup1_web(
                @Field("emp") String emp,
                @Field("cat_id") String cat_id,
                @Field("village_id") String village_id,
                @Field("model_new") String model,
                @Field("payment_type") String payment_type,
                @Field("passing_type") String passing_type,
                @Field("deal_type") String deal_type,
                @Field("maker") String maker,
                @Field("model") String model1,
                @Field("manufacture_year") String manufacture_year
        );
        @POST("deal_stage_follow_up.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> deal_followup2_web(
                @Field("emp") String emp,
                @Field("cat_id") String cat_id,
                @Field("village_id") String village_id,
                @Field("model_new") String model,
                @Field("payment_type") String payment_type,
                @Field("passing_type") String passing_type,
                @Field("deal_type") String deal_type,
                @Field("maker") String maker,
                @Field("model") String model1,
                @Field("manufacture_year") String manufacture_year
        );

        @POST("3rd_follow_up_inq.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> deal_followup3_web(
                @Field("emp") String emp,
                @Field("cat_id") String cat_id,
                @Field("village_id") String village_id,
                @Field("model_new") String model,
                @Field("payment_type") String payment_type,
                @Field("passing_type") String passing_type,
                @Field("deal_type") String deal_type,
                @Field("maker") String maker,
                @Field("model") String model1,
                @Field("manufacture_year") String manufacture_year
        );


        @POST("deal_stage_negotiation.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> deal_negosiation_web(
                @Field("emp") String emp,
                @Field("cat_id") String cat_id,
                @Field("village_id") String village_id,
                @Field("model_new") String model,
                @Field("payment_type") String payment_type,
                @Field("passing_type") String passing_type,
                @Field("deal_type") String deal_type,
                @Field("maker") String maker,
                @Field("model") String model1,
                @Field("manufacture_year") String manufacture_year

        );

        @POST("deal_stage_count.php")
        @FormUrlEncoded
        Call<deal_set_count_model> deal_setCount_web(
                @Field("emp") String emp,
                @Field("login_id") String login_id,
                @Field("cat_id") String catid,
                @Field("village_id") String villageid,
                @Field("model_new") String model,
                @Field("payment_type") String payment_type,
                @Field("passing_type") String passing_type,
                @Field("deal_type") String deal_type,
                @Field("maker") String maker,
                @Field("model") String model1,
                @Field("manufacture_year") String manufacture_year
        );

        @POST("inq_history_display.php")
        @FormUrlEncoded
        Call<deal_stage_history_model> dealstage_history_web(
                @Field("sname") String emp
        );

        @POST("deal_stage_msj.php")
        @FormUrlEncoded
        Call<model_msg> dealstage_msg(
                @Field("deal_type") String deal_type/*,
                @Field("model") String model*/
        );

        @GET("village_list_filter.php")
        Call<DataVillageeModel123> getVillageList123();

        @POST("deal_stage_first_meeting_deal.php")
        @Multipart
        Call<Deal_model> deal_first_meeting_web(
                @Part("login_id") RequestBody login_id,
                @Part("vemp") RequestBody vemp,
                @Part("cat_id") RequestBody cat_id,
                @Part("sname") RequestBody sname,
                @Part("booking") RequestBody booking,
                @Part("booking_amt") RequestBody booking_amt,
                @Part("delivry") RequestBody delivry,
                @Part("model_name") RequestBody model_name,
                @Part("sell_lost") RequestBody sell_lost,
                @Part("sell_model") RequestBody sell_model,
                @Part("drop_inq") RequestBody drop_inq,
                @Part("drop_rea") RequestBody drop_rea,
                @Part("loan_clear") RequestBody loan_clear,
                @Part("d_p_clear") RequestBody d_p_clear,
                @Part("old_rec_book_veh") RequestBody old_rec_book_veh,

                @Part MultipartBody.Part image1,
                @Part MultipartBody.Part image2
        );
       /* @POST("deal_stage_first_meeting_deal.php")
        @FormUrlEncoded
        Call<deal_firstmeeting_deal_model> deal_first_meeting_web(
                @Field("login_id") String login_id,
                @Field("vemp") String vemp,
                @Field("cat_id") String cat_id,
                @Field("sname") String sname,
                @Field("booking") String booking,
                @Field("booking_amt") String booking_amt,
                @Field("delivry") String delivry,
                @Field("model_name") String model_name,
                @Field("sell_lost") String sell_lost,
                @Field("sell_model") String sell_model,
                @Field("drop_inq") String drop_inq,
                @Field("drop_rea") String drop_rea,
                @Field("mobile_no") String mobile_no
        );*/


        @POST("deal_stage_next_acivity_plan.php")
        @Multipart
        Call<deal_threestage_model> deal_3stage_web(
                @Part("id") RequestBody id,
                @Part("next_activity") RequestBody next_activity,
                @Part("sname") RequestBody sname,

                @Part MultipartBody.Part image2,
                @Part MultipartBody.Part image3,
                @Part MultipartBody.Part image4
        );

        @POST(" deal_stage_make_an_offer.php")
        @Multipart
        Call<deal_threestage_model> deal_makeandoffer_web(
                @Part("id") RequestBody id,
                @Part("make_an_offer") RequestBody make_an_offer,
                @Part("sname") RequestBody sname,

                @Part MultipartBody.Part image2,
                @Part MultipartBody.Part image3
        );

        @POST("deal_stage_negotiation_add.php")
        @FormUrlEncoded
        Call<deal_negoatiation_web> dealstage_negoatioaion_web(
                @Field("id") String id,
                @Field("negotiation") String negotiation
        );

        @POST("deler_meeting_list.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> dealstage_dealer_meeting(
                @Field("cat_id") String cat_id,
                @Field("emp") String emp,
                @Field("village_id") String village_id,
                @Field("model_new") String model,
                @Field("payment_type") String payment_type,
                @Field("passing_type") String passing_type,
                @Field("deal_type") String deal_type
        );

        @POST("1st_follow_up_inq.php.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> dealstage_followup1(
                @Field("cat_id") String cat_id,
                @Field("emp") String emp,
                @Field("village_id") String village_id,
                @Field("model") String model,
                @Field("payment_type") String payment_type,
                @Field("passing_type") String passing_type
        );

        @POST("2st_follow_up_inq.php.php")
        @FormUrlEncoded
        Call<Deal_notattend_model> dealstage_followup2(
                @Field("cat_id") String cat_id,
                @Field("emp") String emp,
                @Field("village_id") String village_id,
                @Field("model") String model,
                @Field("payment_type") String payment_type,
                @Field("passing_type") String passing_type
        );

        @POST("deal_stage_delivery_one.php")
        @FormUrlEncoded
        Call<ViewInqMonthlyOneModel> getDealstageDeliverybutton(
                @Field("fdate") String fdate,
                @Field("tdate") String tdate,
                @Field("cat_id") String cat_id,
                @Field("emp_id") String emp_id
        );

        @POST("deal_stage_final.php")
        @FormUrlEncoded
        Call<delivery_screen_model> getdeliveryScreen_web(
                @Field("id") String id,
                @Field("sname") String sname
        );

        @GET("inq_delivey_model.php")
        Call<getModelData> getModel_firstmeeting();


        @POST("overdue_checkout.php")
        @FormUrlEncoded
        Call<chckeout_model> overdue_checkedOUT(
                @Field("emp") String id
        );

        @POST("performance_api.php")
        @FormUrlEncoded
        Call<perfomance_model> getPerfomance(
                @Field("emp") String id,
                @Field("fdate") String fdate,
                @Field("enddate") String enddate/*,
                @Field("village_id") String village_id*/
        );

        @POST("databank_field_display.php")
        @FormUrlEncoded
        Call<DataBankFieldDisplayModel> getDataBankFields(
                @Field("cat_id") String cat_id
        );

        @GET("databank_make.php")
        Call<DatabankMakeModel> getDatabankMake(
        );

        @POST("databank_model_list.php")
        @FormUrlEncoded
        Call<DatabankModelListModel> getDatabankModelList(
                @Field("make_name") String make_name
        );

        @POST("feedback_call_deal_stage.php")
        @FormUrlEncoded
        Call<FeedBackCall> FeedBackCall(
                @Field("emp") String emp,
                @Field("cat_id") String cat_id
        );

        @POST("feedback_call_deal_stage_menu.php")
        @FormUrlEncoded
        Call<FeedBackCall_call> FeedBackCall_call(
                @Field("call_id") String call_id
        );

        @POST("feedback_add.php")
        @FormUrlEncoded
        Call<FeedbackCall_add> FeedBackCall_add(
                @Field("id") String id,
                @Field("employee_vist") String employee_vist,
                @Field("customer_satisfaction") String customer_satisfaction,
                @Field("latest_offer") String latest_offer,
                @Field("intrested") String intrested,
                @Field("remark") String remark
        );

        @POST("common_serach.php")
        @FormUrlEncoded
        Call<CommonSearch> CommonSearch(
                @Field("emp") String emp,
                @Field("cat_id") String cat_id,
                @Field("payment_type") String payment_type,
                @Field("passing_type") String passing_type

        );

        @POST("inq_village_vlist.php")
        @FormUrlEncoded
        Call<VillageList> VillageList(
                @Field("emp") String emp,
                @Field("cat_id") String cat_id
        );

        @POST("data_view_village_wise_inq.php")
        @FormUrlEncoded
        Call<VillageListShow> VillageListShow(
                @Field("vid") String vid,
                @Field("cat_id") String cat_id,
                @Field("emp") String emp
        );

        @POST("myprofile_catlist.php")
        @FormUrlEncoded
        Call<Catlist> Catlist(
                @Field("emp") String emp
        );

        @POST("profile_village_list.php")
        @FormUrlEncoded
        Call<VillageListProfile> VillageListProfile(
                @Field("emp") String emp,
                @Field("cat_id") String cat_id
        );

        @POST("profile_village_wise.php")
        @FormUrlEncoded
        Call<VillageListShowProfile> VillageListShowProfile(
                @Field("vid") String vid,
                @Field("cat_id") String cat_id,
                @Field("emp") String emp
        );
        @POST("score_data_view.php")
        @FormUrlEncoded
        Call<ScoreBoardView> ScoreBoardView(
                @Field("emp_id") String emp_id,
                @Field("fdate") String fdate,
                @Field("tdate") String tdate,
                @Field("type") String type
        );
        @POST("booking_next.php")
        @FormUrlEncoded
        Call<Booking_Next> Booking_Next(
                @Field("id") String id,
                @Field("stage") String stage
        );
        @GET("recbook_update_api.php")
        Call<RcUpdate> RcUpdate();

        @POST("booking_number_fitment.php")
        @FormUrlEncoded
        Call<RcUpdate> GetNumberPlateFitmentList(
                @Field("emp") String id

        );

        /*rc_update_customer.php*/
        @POST("rc_update_customer.php")
        @FormUrlEncoded
        Call<RcUpdate> GetRCUpdateCustomerList(
                @Field("emp") String id


        );

        /*rc_update_final.php*/
        @POST("rc_update_final.php")
        @FormUrlEncoded
        Call<RcUpdate> GetRCUpdateFinancerList(
                @Field("emp") String id


        );

        @POST("rcbook_update_submit.php")
        @Multipart
        Call<RcbookUpdate> NumberPlateUpdate(
                @Part("id") RequestBody id,
                @Part("number_plate_fitment") RequestBody number_plate_fitment,
                @Part("reg_no") RequestBody reg_no,
                @Part MultipartBody.Part image1  ,
                @Part("flag") RequestBody flag

        );

        @POST("final_submit.php")
        @Multipart
        Call<RcbookUpdate> FinalSubmit(
                @Part("id") RequestBody id

        );


        @POST("rcbook_update_submit2.php")
        @Multipart
        Call<RcbookUpdate> RcbookUpdateCustomer(
                @Part("id") RequestBody id,
                @Part("rc_update_customer") RequestBody rc_update_customer,
                @Part MultipartBody.Part image2,
                @Part MultipartBody.Part image3
        );


        @POST("rcbook_update_submit.php")
        @Multipart
        Call<RcbookUpdate> RcbookUpdate(
                @Part("id") RequestBody id,
                @Part("number_plate_fitment") RequestBody number_plate_fitment,
                @Part("rcbook_update") RequestBody rcbook_update,
                @Part("rc_update_customer") RequestBody rc_update_customer,
                @Part("reg_no") RequestBody reg_no,
                @Part MultipartBody.Part image1,
                @Part MultipartBody.Part image2,
                @Part MultipartBody.Part image3
        );
        @POST("payment_village.php")
        @FormUrlEncoded
        Call<VillageList1> VillageList1(
                @Field("type") String type
        );
        @POST("villge_wise_payment_cel_list.php")
        @FormUrlEncoded
        Call<ShowVilageDetail> VillageListShowon(
                @Field("type") String type,
                @Field("vname") String vname
        );
        @GET("new_rec.php")
        Call<Newdata> Newdata(
        );
        @GET("new_rec_sppart.php")
        Call<Newdata> Newdata1(
        );
        @GET("new_payment_cel.php")
        Call<New_new_count> New_new_count(
        );
        @POST("loan_otp_verify.php")
        @FormUrlEncoded
        Call<Loan_formate> Loan_formate(
                @Field("id") String id,
                @Field("otp") String otp
        );
    }
}



