package com.example.kumarGroup

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class CategoryModel(
        val `data`: List<Data>
)

data class Data(
        val cat_id: String,
        val cat_list: String,
        val make_name: String,
        val model_name: String,
        val model_y: String,
        val sor_inq: String,
        val status: String

)

data class DataTaskModel(
        val task: List<DataTask>
)

data class DataTask(
        val call_id: String,
        val cat_id: String,
        val cat_name: String,
        val mobile: String,
        val task: String,
        val task_assign: String,
        val task_take_name: String,
        val task_type: String,
        val village: String,
        val whatsappno: String
)

data class StateModel(
        val `data`: List<Datastate>
)

data class DataDistrictModel(
        val district: List<District>
)

data class DataTehsilModel(
        val tehsil: List<Tehsil>
)



data class DataVillageModel(
        val village: List<Village>
)

data class ChackinModel(
        val msg: String,
        val success: Boolean
)

data class Village(
        val id: String? = null,
        val village_name: String? = null,
        val days: String? = null
)

data class Tehsil(
        val id: String,
        val tehsil_name: String
)


data class DataCountListModel(
        val cat: List<Catemp>
)


data class DataVillageeModel(
        val detail: List<Detailvillage>
)

data class DataVillageeModel123(
        val village: ArrayList<Village> = arrayListOf()
)


data class Detailvillage(
        val eid: String,
        val village_name: String
)


data class AccountLadgerData (

        val tableid      : String? = null,
        val add_date      : String? = null,
        val product      : String? = null,
        val CustomerName : String? = null,
        val model        : String? = null,
        val Description  : String? = null,
        val credit       : String? = null,
        val debit        : String? = null

)

data class Catemp(
        val cat_id: String,
        val cat_list: String,
        val count: String
)




data class DataLoginModel(
        val data: List<LoginData>,
        val msg: String,
        val success: Boolean
)

data class LoginData(
        val emp_id: String,
        val emp_name: String,
        val photo: String,
        val unique_no: String,
        val designation: String,
        val module_id: List<String>,
        val module_name: List<String>
)


data class Cat(
        val cat_id: String,
        val cat_list: String,
        val count: String
)

data class District(
        val district: String,
        val id: String
)

data class Datastate(
        val id: String,
        val state: String
)

data class DatafonameModel(
        val name: List<Name>
)

data class Name(
        val area_id: String,
        val emp_id: String,
        val eno: String,
        val token: String,
        val ename: String
)

data class DataSubmitModel(
        val msg: String,
        val image: String,
        val success: Boolean
)

data class DataCityModel(
        val city: List<City>
)


data class City(
        val city: String,
        val id: String
)


data class DataReaplceModel(
    val msg: String,
    val success: Boolean,
    val task: List<Any>
)

data class LanchBreckModel(
        val msg: String,
        val success: Boolean
)

data class LanchBreckModel2(
        val msg: String,
        val success: Boolean
)

data class DataChackOutModel(
        val msg: String,
        val success: Boolean
)

data class DataChackOutModelno(
        val msg: String,
        val success: Boolean
)

data class DataTaskOverTimeModel(
        val msg: String,
        val success: Boolean
)

data class DataEndOvertimemoel(
        val msg: String,
        val success: Boolean
)

data class DataRemoveCallModel(
        val detail: List<Any>,
        val msg: String,
        val success: Boolean
)

data class RemoveWhatappModel(
        val detail: List<Any>,
        val msg: String,
        val success: Boolean
)

data class AbsentresonModel(
        val message: String,
        val phase: String
)

data class ChackphaseModel(
        val phase: String
)

data class DataHalfDayChackinModel(
        val msg: String,
        val success: Boolean
)

data class DataHalfDayChackOutModel(
        val msg: String,
        val success: Boolean
)

data class DataHalfSundayChackinModel(
        val msg: String,
        val success: Boolean
)

data class DataHalfSundayChackoutModel(
        val msg: String,
        val success: Boolean
)

data class DataTaskCompletModel(
        val msg: String,
        val success: Boolean,
        val task: List<Any>
)

data class DataremoveassignmentModel(
        val detail: List<Any>,
        val msg: String,
        val success: Boolean
)

data class DataAttendancewalletmodel(
        val attandance: List<Attandancewallet>
)

data class Attandancewallet(
        val checkin: String,
        val checkout_time: String,
        val date: String,
        val icard: String,
        val location: String,
        val lunch_e_time: String,
        val overtime_end_time: String,
        val overtime_time: String,
        val payout: Int,
        val status: String,
        val uniform: String,
        val working_hour: String
)


//SalarySleepModel

data class SalarySlipModel(
        val salarysleep: List<Salarysleep>
)

data class Salarysleep(
        val Icard_fine: Int,
        val Location_fine: Int,
        val absent: Int,
        val cdate: String,
        val e_total_salary: Int,
        val edate: String,
        val employee_name: String,
        val extrapay: String,
        val fdate: String,
        val mobile: String,
        val payout: Int,
        val present: String,
        val total_salary: String,
        val type: String,
        val uniform_fine: Int
)

//Add Loan form
data class AddLoanModel(
        val id: String,
        val msg: String,
        val email: String,
        val success: Boolean
)
//Maintenance Employee Vendor list
data class MaintananceEmpVenList(
        val type: List<Type>
)

data class Type(
        val id: String,
        val name: String
)


//Maintenance Add Form
data class MaintenanceAddModel(
        val msg: String,
        val success: Boolean
)

//Display maintenance
data class DisplayMaintenanceModel(
        val maintainance: List<Maintainance>
)

data class Maintainance(
        val Image: String,
        val amount: String,
        val desc: String,
        val main_name: String,
        val main_type: String,
        val mdate: String,
        val status: String,
        val type: String
)


//Display Loan
data class DisplayLoanModel(
        val maintainance: List<MaintainanceLoan>
)

data class MaintainanceLoan(
        val date: String,
        val description: String,
        val employee_name: String,
        val percentage: Double,
        val total_amount: Double,
        val type: String
)

data class DisplayLoanFinalModel(
        val loan_display: List<LoanDisplay>
)

data class LoanDisplay(
        val Credit: String,
        val Date: String,
        val Debit: String,
        val balance: Double,
        val desc: String,
        val emp_name: String,
        val interest: Double,
        val total_amount: Double
)

//Follow Up Category
data class CategoryFollowUpModel(
        val `data`: List<DataCategoryFU>
)

data class DataCategoryFU(
        val cat_id: String,
        val cat_list: String
)

//Follow up Person
data class CustomerFollowUpModel(
        val cat: List<CatFollowUp>
)

data class CatFollowUp(
        val cat_id: String,
        val cat_name: String,
        val city: String,
        val cust_id: String,
        val desc: String,
        val distric: String,
        val employee_name: String,
        val fname: String,
        val moblino: String,
        val state: String,
        val tehsil: String,
        val vilage: String,
        val whatsappno: String
)

//Follow Up Submit
data class FollowUpSubmitModel(
        val msg: String,
        val success: Boolean
)

data class Forgotpassword(
        val detail: List<Detail1>
)

data class Detail1(
        val msg: String,
        val email: String

)


//Follow up Month

data class MonthFollowUpModel(
        val detail: List<Detail>
)

data class Detail(
        val count: Int,
        val id: String,
        val month: String
)


//follow up Week
data class DayFollowUpModel(
        val detail: List<DetailDayFollowUp>
)

data class DetailDayFollowUp(
        val count: Int,
        val day: String,
        val id: String
)


//Follow Up WeeklyData
data class WeeklyFollowUpModel(
        val detail: List<DetailWeeklyFollowUp>
)

data class DetailWeeklyFollowUp(
        val count: Int,
        val day: String,
        val id: String
)



data class DetailWeek(
        val count: String
)


//Daily Follow Up Model
data class DailyFollowUpModel(
        val detail: List<DetailDaily>
)

data class DetailDaily(
        val count: Int,
        val id: String
)

//ShowMonth
data class ShowMonthModel(
        val cat: List<CatShowMonth>
)

data class CatShowMonth(
        val amount: String,
        val booking: String,
        val booking_amt: String,
        val cat_id: String,
        val cat_name: String,
        val city: String,
        val delivry: String,
        val desc: String,
        val distric: String,
        val employee_name: String,
        val fname: String,
        val id: String,
        val lname: String,
        val moblino: String,
        val model_name: String,
        val payment_collection: String,
        val reason: String,
        val state: String,
        val tehsil: String,
        val vdate: String,
        val vilage: String,
        val whatsappno: String,
        val autoid: String
)


//WorkBook Employee List
data class EmployeeListWBModel(
        val `data`: List<DataWBEmployee>
)

data class DataWBEmployee(
        val ename: String,
        val id: String
)


//WorkBook Customer List
data class CustomerListWBModel(
        val `data`: List<DataCustomerListWB>
)

data class DataCustomerListWB(
        val ename: String,
        val id: String,
        val moblino: String,
        val name: String,
        val whatsappno: String
)

//Current date
data class CurrentDateModel(
        val msg: String,
        val success: Boolean
)

//WorkBook Submit
data class WorkBookSubmitModel(
        val msg: String,
        val success: Boolean
)


//Followup Detail Display Submit
data class SubmitDetailShowFollowUpModel(
        val msg: String,
        val success: Boolean
)

data class VisitFollowUpModel(
        val cat: List<CatVisitFollow>
)

data class CatVisitFollow(
        val addgcust: String,
        val amount: String,
        val booking: String,
        val booking_amt: String,
        val cat_id: String,
        val cat_name: String,
        val city: String,
        val delivry: String,
        val desc: String,
        val distric: String,
        val employee_name: String,
        val fname: String,
        val id: String,
        val lname: String,
        val moblino: String,
        val model_name: String,
        val payment_collection: String,
        val reason: String,
        val sell_lost: String,
        val sell_model: Any,
        val state: String,
        val tehsil: String,
        val vdate: String,
        val vilage: String,
        val whatsappno: String,
        val cudate: String
)

data class StartOverTimeModel(
        val id: Int,
        val msg: String,
        val phase: Int,
        val success: Boolean
)


//OverTime Check out
data class OverTimeCheckOutModel(
        val msg: String,
        val success: Boolean
)


data class ReportCollectionCustomerModel(
        val cat: List<CatRCCustomer>
)

data class CatRCCustomer(
        val cate_name: String,
        val catid: String,
        val count: String,
        val eid: String
)

data class ShowDetailGVModel(
        val cat: List<CatShowRCGV>
)

data class CatShowRCGV(
        val cat_id: String,
        val cat_name: String,
        val city: String,
        val desc: String,
        val distric: String,
        val employee_name: String,
        val fname: String,
        val id: String,
        val lname: String,
        val moblino: String,
        val state: String,
        val tehsil: String,
        val vilage: String,
        val whatsappno: String
)


data class OpenVisitRCGVModel(
        val msg: String,
        val success: Boolean
)


//Report Collection

data class RcMonthModel(
        val detail: List<DetailRCMonth>
)

data class DetailRCMonth(
        val count: Int,
        val id: String,
        val month: String
)


//Report Collection Weekly

data class RcWeeklyModel(
        val detail: List<DetailRcWeekly>
)

data class DetailRcWeekly(
        val count: Int,
        val day: String,
        val id: String
)

//Report Collection Daily
data class DailyRCModel(
        val detail: List<DetailDailyRC>
)

data class DetailDailyRC(
        val count: Int,
        val id: String
)


//All Entry Of Month Week Day
data class AllEntryMonthWeekDayRCModel(
        val cat: List<CatAllEntryMWD>
)

data class CatAllEntryMWD(
        val amount: String,
        val autoid: String,
        val booking: String,
        val booking_amt: String,
        val cat_id: String,
        val cat_name: String,
        val city: String,
        val delivry: String,
        val desc: String,
        val distric: String,
        val employee_name: String,
        val fname: String,
        val id: String,
        val lname: String,
        val moblino: String,
        val model_name: String,
        val model: String,
        val payment_collection: String,
        val reason: String,
        val state: String,
        val tehsil: String,
        val vdate: String,
        val vilage: String,
        val whatsappno: String,

        val final_amt: String,
        val add_type: String,
        val add_id: String
)


//Visit Entry RC
data class ShowVisitRCModel(
        val cat: List<CatShowDetailVisitRC>
)

data class CatShowDetailVisitRC(
        val addgcust: String,
        val amount: String,
        val booking: String,
        val booking_amt: String,
        val cat_id: String,
        val cat_name: String,
        val city: String,
        val delivry: String,
        val desc: String,
        val distric: String,
        val employee_name: String,
        val fname: String,
        val id: String,
        val lname: String,
        val moblino: String,
        val model_name: String,
        val payment_collection: String,
        val reason: String,
        val sell_lost: String,
        val sell_model: String,
        val state: String,
        val tehsil: String,
        val vdate: String,
        val vilage: String,
        val whatsappno: String,
        val cudate: String
)

//insert RC visit data
data class InsertRCSecondModel(
        val msg: String,
        val success: Boolean
)


//Notification View
data class NotificationViewModel(
        val `data`: List<DataNotification>
)

data class DataNotification(
        val cat_id: String,
        val cat_name: String,
        val city: String,
        val count: String,
        val desc: String,
        val distric: String,
        val employee_name: String,
        val fname: String,
        val lname: String,
        val moblino: String,
        val state: String,
        val tehsil: String,
        val vilage: String,
        val whatsappno: String,
        val cdate: String,
        val Day: Int,
        val id: String,
        val which: String,
        val status: String
)


//upload Photo Notification
data class PhotoUploadNotificationModel(
        val msg: String,
        val success: Boolean
)


data class NotificationCallRemoveModel(
        val detail: List<Any>,
        val msg: String,
        val success: Boolean
)


/*Delivery/Booking  add Customer Details*/

data class addCustomerDetailStateModel(
        val state: List<State>
)

data class State(
        val id: String,
        val state: String
)


data class addCDCityModel(
        val city: List<CityCD>
)

data class CityCD(
        val city: String,
        val id: String
)

data class addCDDistrict(
        val district: List<DistrictCD>
)

data class DistrictCD(
        val district: String,
        val id: String
)


data class addCDTalukaModel(
        val tehsil: List<TehsilCD>
)

data class TehsilCD(
        val id: String,
        val tehsil_name: String
)

data class addVillageCDModel(
        val detail: List<DetailVillageCD>
)

data class DetailVillageCD(
        val eid: String,
        val village_id: String,
        val village_name: String
)


data class MobileNumDetailModel(
        val cat: List<CatMobile>
)

data class CatMobile(
        val cat_id: String,
        val cat_name: String,
        val city: String,
        val desc: String,
        val distric: String,
        val employee_name: String,
        val fname: String,
        val lname: String,
        val moblino: String,
        val state: String,
        val tehsil: String,
        val vilage: String,
        val whatsappno: String,
        val eid: String,
        val sname: String,
        val login_id: String,
        val inq_id: String,
        val note: String
)

data class ModelNameProductModel(
        val `data`: List<DataModelName>
)

data class DataModelName(
        val id: String,
        val model_name: String
)


//Down payment make
data class MakeDownPaymentModel(
        val state: List<StateMake>
)

data class StateMake(
        val id: String,
        val make: String
)


//Down Payment Model
data class ModelDownPaymentModel(
        val model: List<Model>
)

data class Model(
        val model_name: String
)


//Customer Detail Main API
data class CustomerDetailFinalModel(
        val id: Int,
        val msg: String,
        val success: Boolean
)

data class ProductDetailNextModel(
        val id: Int,
        val msg: String,
        val success: Boolean
)

//Other Product Detail
data class OtherProductModel(
        val id: Int,
        val msg: String,
        val success: Boolean
)

//Price Detail
data class PriceDetailNextModel(
        val msg: String,
        val success: Boolean
)


//RTO Detail
data class RTODetailNextModel(
        val msg: String,
        val success: Boolean
)


//Phase API
data class PhaseAddBookingModel(
        val phase: String
)


//Down Payment

data class DownPaymentNextModel(
        val msg: String,
        val success: Boolean
)


//consumer Skim
data class ConsumerSkimSubmitModel(
        val msg: String,
        val success: Boolean
)


//consumer Skim
data class MainTainanceType(
         val data: List<MaintainanceFilter>
)


data class MaintainanceFilter(
        val type: String,
        val description: String
)

// Booking new model
data class BookingUploadModel(
        // val `data`: List<DataBooking>,
        val data: List<DataBooking>,
        val error: Boolean,
        val message: String
)

data class DataBooking(
        val adhar_photo: String,
        val adhar_photo_check: String,
        val agent_fee: String,
        val agent_fee_check: String,
        val amount: String,
        val amount_check: String,
        val app_status: String,
        val approve_status: String,
        val b_date: String,
        val b_date_check: String,
        val b_p_photo: String,
        val b_p_photo_check: String,
        val b_photo: String,
        val empid: String,
        val b_photo_check: String,
        val b_type: String,
        val b_type_check: String,
        val bno: String,
        val bno_check: String,
        val booking_amt: String,
        val booking_amt_check: String,
        val bumper: String,
        val bumper_check: String,
        val c_amount: String,
        val c_amount_check: String,
        val check1_photo: String,
        val check1_photo_check: String,
        val check_amt: String,
        val check_amt_check: String,
        val check_date: String,
        val check_date_check: String,
        val check_neft_rtgs: String,
        val check_neft_rtgs_check: String,
        val check_photo: String,
        val check_photo_check: String,
        val city: String,
        val city_check: String,
        val deal_price: String,
        val deal_price_check: String,
        val deal_price_in_word: String,
        val deal_price_in_word_check: String,
        val description: String,
        val description_check: String,
        val distric: String,
        val distric_check: String,
        val drowbar: String,
        val drowbar_check: String,
        val ec_photo: String,
        val ec_photo_check: String,
        val ecard_photo: String,
        val ecard_photo_check: String,
        val emp: String,
        val emp_check: String,
        val emp_id: String,
        val emp_id_check: String,
        val fname: String,
        val fname_check: String,
        val gst: String,
        val gst_check: String,
        val hitch: String,
        val hitch_check: String,
        val hood: String,
        val hood_check: String,
        val id: String,
        val insurance: String,
        val insurance_check: String,
        val lname: Any,
        val lname_check: String,
        val loan_charge: String,
        val loan_charge_check: String,
        val m_year: String,
        val m_year_check: String,
        val make: String,
        val make_check: String,
        val mobileno: String,
        val mobileno_check: String,
        val model: String,
        val model_check: String,
        val model_name: String,
        val model_name_check: String,
        val neft_rtgs_amt: String,
        val neft_rtgs_amt_check: String,
        val neft_rtgs_date: String,
        val neft_rtgs_date_check: String,
        val neft_rtgs_photo: String,
        val neft_rtgs_photo_check: String,
        val noc: String,
        val noc_check: String,
        val noc_photo: String,
        val noc_photo_check: String,
        val number_plat: String,
        val number_plat_check: String,
        val old_t_amount: String,
        val old_t_amount_check: String,
        val old_vehicle_photo: String,
        val old_vehicle_photo_check: String,
        val p_desc: String,
        val p_desc_check: String,
        val p_photo: String,
        val p_photo_check: String,
        val paper_expence: String,
        val paper_expence_check: String,
        val product_name: String,
        val product_name_check: String,
        val recbook_photo: String,
        val recbook_photo_check: String,
        val ref_name: String,
        val ref_name_check: String,
        val ref_no: String,
        val ref_no_check: String,
        val rto: String,
        val rto_check: String,
        val rto_passing: String,
        val rto_passing_check: String,
        val rto_tax: String,
        val rto_tax_check: String,
        val sname: String,
        val sname_check: String,
        val state: String,
        val state_check: String,
        val tehsill: String,
        val tehsill_check: String,
        val toolkit: String,
        val toolkit_check: String,
        val toplink: String,
        val toplink_check: String,
        val village: String,
        val village_check: String,
        val whno: String,
        val whno_check: String,

        val skim: String,
        val atype: String,
        val skim_check: String,
        val atype_check: String,

        val adhar_back: String,
        val adhar_back_check: String,
        val election_back: String,
        val election_back_check: String,
        val p_photo_back: String,
        val p_photo_back_check: String,
        val rcbook_back: String,
        val rcbook_back_check: String,
        val elec_back: String,
        val elec_back_check: String,
        val b_pass_back: String,
        val b_pass_back_check: String,
        val cskim: String,
        val dmodelname: String
)

data class BookingSubmitModel(
        val message: String
)


//Loan Submit Form
data class LoanSubmitBookingModel(
        val message: String
)

// Loan data Display
data class LoanDataDisplayModel(
        // val `data`: List<DataLoan>,
        val data: List<DataLoan>,
        val error: Boolean,
        val message: String
)

data class DataLoan(
        val adhar_photo: String,
        val adhar_photo_check: String,
        val agent_fee: String,
        val agent_fee_check: String,
        val amount: String,
        val amount_check: String,
        val app_status: String,
        val approve_status: String,
        val b_date: String,
        val b_date_check: String,
        val b_p_photo: String,
        val b_p_photo_check: String,
        val b_photo: String,
        val b_photo_check: String,
        val b_type: String,
        val b_type_check: String,
        val bno: String,
        val bno_check: String,
        val booking_amt: String,
        val booking_amt_check: String,
        val booking_sub: String,
        val bumper: String,
        val bumper_check: String,
        val c_amount: String,
        val c_amount_check: String,
        val check1_photo: String,
        val check1_photo_check: String,
        val check_amt: String,
        val check_amt_check: String,
        val check_date: String,
        val check_date_check: String,
        val check_neft_rtgs: String,
        val check_neft_rtgs_check: String,
        val check_photo: String,
        val check_photo_check: String,
        val cibil_score: Any,
        val cibil_score_check: String,
        val city: String,
        val city_check: String,
        val deal_price: String,
        val deal_price_check: String,
        val deal_price_in_word: String,
        val deal_price_in_word_check: String,
        val description: String,
        val description_check: String,
        val distric: String,
        val distric_check: String,
        val drowbar: String,
        val drowbar_check: String,
        val ec_photo: String,
        val ec_photo_check: String,
        val ecard_photo: String,
        val ecard_photo_check: String,
        val emp: String,
        val emp_check: String,
        val emp_id: String,
        val emp_id_check: String,
        val fi_date: Any,
        val fi_date_check: String,
        val finance_from: Any,
        val finance_from_check: String,
        val fname: String,
        val fname_check: String,
        val gst: String,
        val gst_check: String,
        val hitch: String,
        val hitch_check: String,
        val hood: String,
        val hood_check: String,
        val id: String,
        val insurance: String,
        val insurance_check: String,
        val l_c_photo: Any,
        val l_c_photo_check: String,
        val l_sec_amt: Any,
        val l_sec_amt_check: String,
        val land_details: Any,
        val land_details_check: String,
        val lb_pb_photo: Any,
        val lb_pb_photo_check: String,
        val lloan_charge: Any,
        val lloan_charge_check: String,
        val lname: Any,
        val lname_check: String,
        val loan_amount: String,
        val loan_amount_check: String,
        val loan_charge: String,
        val loan_charge_check: String,
        val m_year: String,
        val m_year_check: String,
        val make: String,
        val make_check: String,
        val mobileno: String,
        val mobileno_check: String,
        val model: String,
        val model_check: String,
        val model_name: String,
        val model_name_check: String,
        val neft_rtgs_amt: String,
        val neft_rtgs_amt_check: String,
        val neft_rtgs_date: String,
        val neft_rtgs_date_check: String,
        val neft_rtgs_photo: String,
        val neft_rtgs_photo_check: String,
        val noc: String,
        val noc_check: String,
        val noc_photo: String,
        val noc_photo_check: String,
        val number_plat: String,
        val number_plat_check: String,
        val old_t_amount: String,
        val old_t_amount_check: String,
        val old_vehicle_photo: String,
        val old_vehicle_photo_check: String,
        val p_desc: String,
        val p_desc_check: String,
        val p_photo: String,
        val p_photo_check: String,
        val paper_expence: String,
        val paper_expence_check: String,
        val product_name: String,
        val product_name_check: String,
        val r_e_name: String,
        val r_e_name_check: String,
        val recbook_photo: String,
        val recbook_photo_check: String,
        val ref_name: String,
        val ref_name_check: String,
        val ref_no: String,
        val ref_no_check: String,
        val rto: String,
        val rto_check: String,
        val rto_passing: String,
        val rto_passing_check: String,
        val rto_tax: String,
        val rto_tax_check: String,
        val sar_id_photo: Any,
        val sar_id_photo_check: String,
        val sign_veri: Any,
        val sign_veri_check: String,
        val sname: String,
        val sname_check: String,
        val stage: Any,
        val stage_check: String,
        val state: String,
        val state_check: String,
        val tehsill: String,
        val tehsill_check: String,
        val toolkit: String,
        val toolkit_check: String,
        val toplink: String,
        val toplink_check: String,
        val village: String,
        val village_check: String,
        val whno: String,
        val whno_check: String,
        val skim: String,
        val atype: String,
        val skim_check: String,
        val atype_check: String,
        val adhar_back: String,
        val adhar_back_check: String,
        val election_back: String,
        val election_back_check: String,
        val p_photo_back: String,
        val p_photo_back_check: String,
        val rcbook_back: String,
        val rcbook_back_check: String,
        val elec_back: String,
        val elec_back_check: String,
        val b_pass_back: String,
        val b_pass_back_check: String,
        val sectiondate: String,
        val sectiondate_check: String,
        val loan_next_click: String,
        val rto_new: String,
        val cskim: String,
        val dmodelname: String
)


//Finance Form Model
data class FinanceFormModel(
        val finance: List<Finance>
)

data class Finance(
        val fcname: String,
        val id: String
)

data class DeliveryBookingModel(
        val message: String
)

//Document Print Model
data class DocumentPrintDataModel(
        val data: List<DataDocPrint>,
        val error: Boolean,
        val message: String
)

data class DataDocPrint(
        val adhar_photo: String,
        val adhar_photo_check: String,
        val agent_fee: String,
        val agent_fee_check: String,
        val amount: String,
        val amount_check: String,
        val app_status: String,
        val approve_status: String,
        val b_date: String,
        val b_date_check: String,
        val b_p_photo: String,
        val b_p_photo_check: String,
        val b_photo: String,
        val b_photo_check: String,
        val b_type: String,
        val b_type_check: String,
        val battery: String,
        val battery_check: String,
        val bno: String,
        val bno_check: String,
        val booking_amt: String,
        val booking_amt_check: String,
        val booking_sub: String,
        val bumper: String,
        val bumper_check: String,
        val c_amount: String,
        val c_amount_check: String,
        val chasis_print: String,
        val chasis_print_check: String,
        val chasisno: String,
        val chasisno_check: String,
        val check1_photo: String,
        val check1_photo_check: String,
        val check_amt: String,
        val check_amt_check: String,
        val check_date: String,
        val check_date_check: String,
        val check_neft_rtgs: String,
        val check_neft_rtgs_check: String,
        val check_photo: String,
        val check_photo_check: String,
        val cibil_score: String,
        val cibil_score_check: String,
        val city: String,
        val city_check: String,
        val deal_price: String,
        val deal_price_check: String,
        val deal_price_in_word: String,
        val deal_price_in_word_check: String,
        val delivery_date: String,
        val delivery_date_check: String,
        val delivry_photo: String,
        val delivry_photo_check: String,
        val description: String,
        val description_check: String,
        val distric: String,
        val distric_check: String,
        val dmodelname: String,
        val dmodelname_check: String,
        val drowbar: String,
        val drowbar_check: String,
        val ec_photo: String,
        val ec_photo_check: String,
        val ecard_photo: String,
        val ecard_photo_check: String,
        val emp: String,
        val emp_check: String,
        val emp_id: String,
        val emp_id_check: String,
        val engineno: String,
        val engineno_check: String,
        val fi_date: String,
        val fi_date_check: String,
        val filter: Any,
        val finance_from: String,
        val finance_from_check: String,
        val fname: String,
        val fname_check: String,
        val gst: String,
        val gst_check: String,
        val hitch: String,
        val hitch_check: String,
        val hood: String,
        val hood_check: String,
        val id: String,
        val insurance: String,
        val insurance_check: String,
        val l_c_photo: String,
        val l_c_photo_check: String,
        val l_sec_amt: String,
        val l_sec_amt_check: String,
        val land_details: String,
        val land_details_check: String,
        val lb_pb_photo: String,
        val lb_pb_photo_check: String,
        val lloan_charge: String,
        val lloan_charge_check: String,
        val lname: Any,
        val lname_check: String,
        val loan_amount: String,
        val loan_amount_check: String,
        val loan_charge: String,
        val loan_charge_check: String,
        val m_year: String,
        val m_year_check: String,
        val make: String,
        val make_check: String,
        val mobileno: String,
        val mobileno_check: String,
        val model: String,
        val model_check: String,
        val model_name: String,
        val model_name_check: String,
        val neft_rtgs_amt: String,
        val neft_rtgs_amt_check: String,
        val neft_rtgs_date: String,
        val neft_rtgs_date_check: String,
        val neft_rtgs_photo: String,
        val neft_rtgs_photo_check: String,
        val noc: String,
        val noc_check: String,
        val noc_photo: String,
        val noc_photo_check: String,
        val number_plat: String,
        val number_plat_check: String,
        val old_t_amount: String,
        val old_t_amount_check: String,
        val old_vehicle_photo: String,
        val old_vehicle_photo_check: String,
        val p_desc: String,
        val p_desc_check: String,
        val p_photo: String,
        val p_photo_check: String,
        val paper_expence: String,
        val paper_expence_check: String,
        val product_name: String,
        val product_name_check: String,
        val r_e_name: String,
        val r_e_name_check: String,
        val recbook_photo: String,
        val recbook_photo_check: String,
        val ref_name: String,
        val ref_name_check: String,
        val ref_no: String,
        val ref_no_check: String,
        val rto: String,
        val rto_check: String,
        val rto_passing: String,
        val rto_passing_check: String,
        val rto_tax: String,
        val rto_tax_check: String,
        val sale_return: String,
        val sar_id_photo: String,
        val sar_id_photo_check: String,
        val sectiondate: String,
        val sectiondate_check: String,
        val sign_veri: String,
        val sign_veri_check: String,
        val sname: String,
        val sname_check: String,
        val stage: String,
        val stage_check: String,
        val state: String,
        val state_check: String,
        val tehsill: String,
        val tehsill_check: String,
        val toolkit: String,
        val toolkit_check: String,
        val toplink: String,
        val toplink_check: String,
        val tyre: String,
        val tyre_check: String,
        val variants: String,
        val variants_check: String,
        val village: String,
        val village_check: String,
        val whno: String,
        val whno_check: String,
        val skim: String,
        val atype: String,
        val skim_check: String,
        val atype_check: String,
        val adhar_back: String,
        val adhar_back_check: String,
        val election_back: String,
        val election_back_check: String,
        val p_photo_back: String,
        val p_photo_back_check: String,
        val rcbook_back: String,
        val rcbook_back_check: String,
        val elec_back: String,
        val elec_back_check: String,
        val b_pass_back: String,
        val b_pass_back_check: String,

        val cash_date: String,
        val cash_date_check: String,
        val cash_amount: String,
        val cash_amount_check: String,
        val cash_description: String,
        val cash_description_check: String,
        val cskim: String
)


//Passing Data Display
data class PassingDataModel(
        //  val `data`: List<Data>,
        val data: List<DataPassing>,
        val error: Boolean,
        val message: String
)

data class DataPassing(
        val Agent_free: Any,
        val Policy_date: Any,
        val adhar_photo: String,
        val adhar_photo_check: String,
        val agent_fee: String,
        val agent_fee_check: String,
        val amount: String,
        val amount_check: String,
        val app_status: String,
        val approve_status: String,
        val b_date: String,
        val b_date_check: String,
        val b_p_photo: String,
        val b_p_photo_check: String,
        val b_photo: String,
        val b_photo_check: String,
        val b_type: String,
        val b_type_check: String,
        val battery: String,
        val battery_check: String,
        val bno: String,
        val bno_check: String,
        val booking_amt: String,
        val booking_amt_check: String,
        val booking_sub: String,
        val bumper: String,
        val bumper_check: String,
        val c_amount: String,
        val c_amount_check: String,
        val chasis_print: String,
        val chasis_print_check: String,
        val chasisno: String,
        val chasisno_check: String,
        val check1_photo: String,
        val check1_photo_check: String,
        val check_amt: String,
        val check_amt_check: String,
        val check_date: String,
        val check_date_check: String,
        val check_neft_rtgs: String,
        val check_neft_rtgs_check: String,
        val check_photo: String,
        val check_photo_check: String,
        val cibil_score: String,
        val cibil_score_check: String,
        val city: String,
        val city_check: String,
        val crtm: Any,
        val deal_price: String,
        val deal_price_check: String,
        val deal_price_in_word: String,
        val deal_price_in_word_check: String,
        val delivery_date: String,
        val delivery_date_check: String,
        val delivry_photo: String,
        val delivry_photo_check: String,
        val description: String,
        val description_check: String,
        val distric: String,
        val distric_check: String,
        val dmodelname: String,
        val dmodelname_check: String,
        val drowbar: String,
        val drowbar_check: String,
        val ec_photo: String,
        val ec_photo_check: String,
        val ecard_photo: String,
        val ecard_photo_check: String,
        val emp: String,
        val emp_check: String,
        val emp_id: String,
        val emp_id_check: String,
        val engineno: String,
        val engineno_check: String,
        val expriy_date: Any,
        val fi_date: String,
        val fi_date_check: String,
        val filter: Any,
        val finance_from: String,
        val finance_from_check: String,
        val fitment: Any,
        val fname: String,
        val fname_check: String,
        val gst: String,
        val gst_check: String,
        val hitch: String,
        val hitch_check: String,
        val hood: String,
        val hood_check: String,
        val id: String,
        val insurance: String,
        val insurance_check: String,
        val insurance_com_name: Any,
        val l_c_photo: String,
        val l_c_photo_check: String,
        val l_sec_amt: String,
        val l_sec_amt_check: String,
        val land_details: String,
        val land_details_check: String,
        val lb_pb_photo: String,
        val lb_pb_photo_check: String,
        val lloan_charge: String,
        val lloan_charge_check: String,
        val lname: Any,
        val lname_check: String,
        val loan_amount: String,
        val loan_amount_check: String,
        val loan_charge: String,
        val loan_charge_check: String,
        val m_year: String,
        val m_year_check: String,
        val make: String,
        val make_check: String,
        val mobileno: String,
        val mobileno_check: String,
        val model: String,
        val model_check: String,
        val model_name: String,
        val model_name_check: String,
        val neft_rtgs_amt: String,
        val neft_rtgs_amt_check: String,
        val neft_rtgs_date: String,
        val neft_rtgs_date_check: String,
        val neft_rtgs_photo: String,
        val neft_rtgs_photo_check: String,
        val noc: String,
        val noc_check: String,
        val noc_photo: String,
        val noc_photo_check: String,
        val num_plate_order: Any,
        val num_plate_recive: Any,
        val number_plat: String,
        val number_plat_check: String,
        val old_t_amount: String,
        val old_t_amount_check: String,
        val old_vehicle_photo: String,
        val old_vehicle_photo_check: String,
        val p_desc: String,
        val p_desc_check: String,
        val p_photo: String,
        val p_photo_check: String,
        val paper_expence: String,
        val paper_expence_check: String,
        val pinsurance: Any,
        val policy_amount: Any,
        val policy_number: Any,
        val product_name: String,
        val product_name_check: String,
        val r_e_name: String,
        val r_e_name_check: String,
        val rc_book_financial: Any,
        val rc_book_update_in: Any,
        val rc_update: Any,
        val recbook_photo: String,
        val recbook_photo_check: String,
        val recive_date: Any,
        val ref_name: String,
        val ref_name_check: String,
        val ref_no: String,
        val ref_no_check: String,
        val registration_num: Any,
        val rto: String,
        val rto_check: String,
        val rto_passing: String,
        val rto_passing_check: String,
        val rto_tax: String,
        val rto_tax_check: String,
        val sale_return: String,
        val sar_id_photo: String,
        val sar_id_photo_check: String,
        val sectiondate: String,
        val sectiondate_check: String,
        val sign_veri: String,
        val sign_veri_check: String,
        val sname: String,
        val sname_check: String,
        val stage: String,
        val stage_check: String,
        val state: String,
        val state_check: String,
        val tax: Any,
        val tax_amount: Any,
        val tehsill: String,
        val tehsill_check: String,
        val toolkit: String,
        val toolkit_check: String,
        val toplink: String,
        val toplink_check: String,
        val tyre: String,
        val tyre_check: String,
        val variants: String,
        val variants_check: String,
        val village: String,
        val village_check: String,
        val whno: String,
        val whno_check: String,
        val skim: String,
        val atype: String,
        val skim_check: String,
        val atype_check: String,
        val adhar_back: String,
        val adhar_back_check: String,
        val election_back: String,
        val election_back_check: String,
        val p_photo_back: String,
        val p_photo_back_check: String,
        val rcbook_back: String,
        val rcbook_back_check: String,
        val elec_back: String,
        val elec_back_check: String,
        val b_pass_back: String,
        val b_pass_back_check: String,

        val cash_date: String,
        val cash_date_check: String,
        val cash_amount: String,
        val cash_amount_check: String,
        val cash_description: String,
        val cash_description_check: String,

        //Passing Details
        val register_no: String,
        val cskim: String

)

data class PaymentPendingModel(
        val data: List<DataPaymentPending>,
        val error: Boolean,
        val message: String
)

data class DataPaymentPending(
        val Agent_free: String,
        val Policy_date: String,
        val add_date: String,
        val adhar_back: String,
        val adhar_back_check: String,
        val adhar_photo: String,
        val adhar_photo_check: String,
        val agent_fee: String,
        val agent_fee_check: String,
        val amount: String,
        val amount_check: String,
        val amount_word: String,
        val app_status: String,
        val approve_status: String,
        val atype: String,
        val atype_check: String,
        val b_date: String,
        val b_date_check: String,
        val b_p_photo: String,
        val b_p_photo_check: String,
        val b_pass_back: String,
        val b_pass_back_check: String,
        val b_photo: String,
        val b_photo_check: String,
        val b_type: String,
        val b_type_check: String,
        val battery: String,
        val battery_check: String,
        val bno: String,
        val bno_check: String,
        val booking_amt: String,
        val booking_amt_check: String,
        val booking_exit: String,
        val booking_sub: String,
        val bumper: String,
        val bumper_check: String,
        val c_amount: String,
        val c_amount_check: String,
        val cash_amount: String,
        val cash_amount_check: String,
        val cash_date: String,
        val cash_date_check: String,
        val cash_description: String,
        val cash_description_check: String,
        val chasis_print: String,
        val chasis_print_check: String,
        val chasisno: String,
        val chasisno_check: String,
        val check1_photo: String,
        val check1_photo_check: String,
        val check_amt: String,
        val check_amt_check: String,
        val check_amt_word: String,
        val check_date: String,
        val check_date_check: String,
        val check_neft_rtgs: String,
        val check_neft_rtgs_check: String,
        val check_photo: String,
        val check_photo_check: String,
        val cibil_score: String,
        val cibil_score_check: String,
        val city: String,
        val city_check: String,
        val crtm: String,
        val deal_price: String,
        val deal_price_check: String,
        val deal_price_in_word: String,
        val deal_price_in_word_check: String,
        val delivery_date: String,
        val delivery_date_check: String,
        val delivry_photo: String,
        val delivry_photo_check: String,
        val description: String,
        val description_check: String,
        val distric: String,
        val distric_check: String,
        val dmodelname: String,
        val dmodelname_check: String,
        val drowbar: String,
        val drowbar_check: String,
        val ec_photo: String,
        val ec_photo_check: String,
        val ecard_photo: String,
        val ecard_photo_check: String,
        val elec_back: String,
        val elec_back_check: String,
        val election_back: String,
        val election_back_check: String,
        val emp: String,
        val emp_check: String,
        val emp_id: String,
        val emp_id_check: String,
        val empid: String,
        val engineno: String,
        val engineno_check: String,
        val expriy_date: String,
        val fi_date: String,
        val fi_date_check: String,
        val filter: Any,
        val finance_from: String,
        val finance_from_check: String,
        val fitment: String,
        val fname: String,
        val fname_check: String,
        val gst: String,
        val gst_check: String,
        val hitch: String,
        val hitch_check: String,
        val hood: String,
        val hood_check: String,
        val id: String,
        val insurance: String,
        val insurance_check: String,
        val insurance_com_name: String,
        val l_c_photo: String,
        val l_c_photo_check: String,
        val l_sec_amt: String,
        val l_sec_amt_check: String,
        val land_details: String,
        val land_details_check: String,
        val lb_pb_photo: String,
        val lb_pb_photo_check: String,
        val lloan_charge: String,
        val lloan_charge_check: String,
        val lname: Any,
        val lname_check: String,
        val loan_amount: String,
        val loan_amount_check: String,
        val loan_charge: String,
        val loan_charge_check: String,
        val loans: String,
        val m_year: String,
        val m_year_check: String,
        val make: String,
        val make_check: String,
        val mobileno: String,
        val mobileno_check: String,
        val model: String,
        val model_check: String,
        val model_name: String,
        val model_name_check: String,
        val neft_rtgs_amt: String,
        val neft_rtgs_amt_check: String,
        val neft_rtgs_amt_word: String,
        val neft_rtgs_date: String,
        val neft_rtgs_date_check: String,
        val neft_rtgs_photo: String,
        val neft_rtgs_photo_check: String,
        val noc: String,
        val noc_check: String,
        val noc_photo: String,
        val noc_photo_check: String,
        val num_plate_order: String,
        val num_plate_recive: String,
        val number_plat: String,
        val number_plat_check: String,
        val old_t_amount: String,
        val old_t_amount_check: String,
        val old_t_amt_word: String,
        val old_vehicle_photo: String,
        val old_vehicle_photo_check: String,
        val p_desc: String,
        val p_desc_check: String,
        val p_photo: String,
        val p_photo_back: String,
        val p_photo_back_check: String,
        val p_photo_check: String,
        val paper_expence: String,
        val paper_expence_check: String,
        val pinsurance: String,
        val policy_amount: String,
        val policy_number: String,
        val product_name: String,
        val product_name_check: String,
        val r_e_name: String,
        val r_e_name_check: String,
        val rc_book_financial: String,
        val rc_book_update_in: String,
        val rc_update: String,
        val rcbook_back: String,
        val rcbook_back_check: String,
        val recbook_photo: String,
        val recbook_photo_check: String,
        val recive_date: String,
        val ref_name: String,
        val ref_name_check: String,
        val ref_no: String,
        val ref_no_check: String,
        val registration_num: String,
        val rto: String,
        val rto_check: String,
        val rto_passing: String,
        val rto_passing_check: String,
        val rto_tax: String,
        val rto_tax_check: String,
        val sale_return: String,
        val sar_id_photo: String,
        val sar_id_photo_check: String,
        val sectiondate: String,
        val sectiondate_check: String,
        val sign_veri: String,
        val sign_veri_check: String,
        val skim: String,
        val skim_check: String,
        val sname: String,
        val sname_check: String,
        val stage: String,
        val stage_check: String,
        val state: String,
        val state_check: String,
        val tax: String,
        val tax_amount: String,
        val tehsill: String,
        val tehsill_check: String,
        val toolkit: String,
        val toolkit_check: String,
        val toplink: String,
        val toplink_check: String,
        val total_amt: Any,
        val tyre: String,
        val tyre_check: String,
        val variants: String,
        val variants_check: String,
        val village: String,
        val village_check: String,
        val whno: String,
        val whno_check: String,

        val final_amt: String,
        val finish: String
)
data class Payment_PayPenModel(
        val cat: List<CatPayment>
)

data class CatPayment(
        val Old_Vehicle_amount: String,
        val add_date: String,
        val battery: String,
        val bno: String,
        val camt: String,
        val cashamount: String,
        val cashamt: String,
        val cashdate: String,
        val cashdesc: String,
        val chasisno: String,
        val cpdelivery_date: String,
        val cudesc: String,
        val deal_price: String,
        val delivery_date: String,
        val desc: String,
        val distric: String,
        val dmodelname: String,
        val engineno: String,
        val exdate1: String,
        val exdesc1: String,
        val exprice1: String,
        val finance_from: String,
        val fname: String,
        val loanamt: String,
        val loandate: String,
        val loandesc: String,
        val mobileno: String,
        val ptypef: String,
        val ptypeo: String,
        val tehsill: String,
        val tp: Int,
        val tyre: String,
        val variants: String,
        val village: String,

        val uprice: String,
        val udate: String,
        val udesc: String,

        val uprice1: String,
        val udate1: String,
        val udesc1: String,

        val exprice2: String,
        val exdate2: String,
        val exdesc2: String,

        val exprice3: String,
        val exdate3: String,
        val exdesc3: String,

        val ptypes: String,
        val ptypet: String,

        val Chequeamount: String,
        val NEFT_RTGSamount: String,

        val ref1: String,
        val refdate: String,
        val refdesc: String,

        val odesc: String,
        val other_amt: String,
        val datee: String,

        val work_deal_price: String,
        val work_date: String,
        val work_desc: String,

        val work_com_price: String,
        val work_com_date: String,
        val work_com_desc: String,

        val work_de_price: String,
        val work_de_date: String,
        val work_de_desc: String,

        val work_cu_cash_price: String,
        val work_cu_cash_date: String,
        val work_cu_cash_desc: String,

        val work_cu_che_price: String,
        val work_cu_che_date: String,
        val work_cu_che_desc: String,

        val work_cu_nert_price: String,
        val work_cu_nert_date: String,
        val work_cu_nert_desc: String,

        val dexdate1: String,
        val dexdate2: String,
        val dexdate3: String,
        val dexdesc1: String,
        val dexdesc2: String,
        val dexdesc3: String,
        val dexprice1: Int,
        val dexprice2: Int,
        val dexprice3: Int

)


// B_DELIVERY MODEL
data class DeliveryDataDisplayModel(
        val data: List<DataDisplay>,
        val error: Boolean,
        val message: String
)

data class DataDisplay(
        val Agent_free: Any,
        val Policy_date: Any,
        val add_date: String,
        val adhar_back: String,
        val adhar_back_check: String,
        val adhar_photo: String,
        val adhar_photo_check: String,
        val agent_fee: String,
        val agent_fee_check: String,
        val amount: String,
        val amount_check: String,
        val app_status: String,
        val approve_status: String,
        val atype: String,
        val atype_check: String,
        val b_date: String,
        val b_date_check: String,
        val b_p_photo: String,
        val b_p_photo_check: String,
        val b_pass_back: String,
        val b_pass_back_check: String,
        val b_photo: String,
        val b_photo_check: String,
        val b_type: String,
        val b_type_check: String,
        val battery: String,
        val battery_check: String,
        val bno: String,
        val bno_check: String,
        val booking_amt: String,
        val booking_amt_check: String,
        val booking_sub: String,
        val bumper: String,
        val bumper_check: String,
        val c_amount: String,
        val c_amount_check: String,
        val cash_amount: String,
        val cash_amount_check: String,
        val cash_date: String,
        val cash_date_check: String,
        val cash_description: String,
        val cash_description_check: String,
        val chasis_print: String,
        val chasis_print_check: String,
        val chasisno: Any,
        val chasisno_check: String,
        val check1_photo: String,
        val check1_photo_check: String,
        val check_amt: String,
        val check_amt_check: String,
        val check_date: String,
        val check_date_check: String,
        val check_neft_rtgs: String,
        val check_neft_rtgs_check: String,
        val check_photo: String,
        val check_photo_check: String,
        val cibil_score: String,
        val cibil_score_check: String,
        val city: String,
        val city_check: String,
        val crtm: Any,
        val deal_price: String,
        val deal_price_check: String,
        val deal_price_in_word: String,
        val deal_price_in_word_check: String,
        val delivery_date: String,
        val delivery_date_check: String,
        val delivry_photo: String,
        val delivry_photo_check: String,
        val description: String,
        val description_check: String,
        val distric: String,
        val distric_check: String,
        val dmodelname: Any,
        val dmodelname_check: String,
        val drowbar: String,
        val drowbar_check: String,
        val ec_photo: String,
        val ec_photo_check: String,
        val ecard_photo: String,
        val ecard_photo_check: String,
        val elec_back: String,
        val elec_back_check: String,
        val election_back: String,
        val election_back_check: String,
        val emp: String,
        val emp_check: String,
        val emp_id: String,
        val emp_id_check: String,
        val engineno: Any,
        val engineno_check: String,
        val expriy_date: Any,
        val fi_date: String,
        val fi_date_check: String,
        val filter: Any,
        val finance_from: String,
        val finance_from_check: String,
        val fitment: Any,
        val fname: String,
        val fname_check: String,
        val gst: String,
        val gst_check: String,
        val hitch: String,
        val hitch_check: String,
        val hood: String,
        val hood_check: String,
        val id: String,
        val insurance: String,
        val insurance_check: String,
        val insurance_com_name: Any,
        val l_c_photo: String,
        val l_c_photo_check: String,
        val l_sec_amt: String,
        val l_sec_amt_check: String,
        val land_details: String,
        val land_details_check: String,
        val lb_pb_photo: String,
        val lb_pb_photo_check: String,
        val lloan_charge: String,
        val lloan_charge_check: String,
        val lname: Any,
        val lname_check: String,
        val loan_amount: String,
        val loan_amount_check: String,
        val loan_charge: String,
        val loan_charge_check: String,
        val loans: String,
        val m_year: String,
        val m_year_check: String,
        val make: String,
        val make_check: String,
        val mobileno: String,
        val mobileno_check: String,
        val model: String,
        val model_check: String,
        val model_name: String,
        val model_name_check: String,
        val neft_rtgs_amt: String,
        val neft_rtgs_amt_check: String,
        val neft_rtgs_date: String,
        val neft_rtgs_date_check: String,
        val neft_rtgs_photo: String,
        val neft_rtgs_photo_check: String,
        val noc: String,
        val noc_check: String,
        val noc_photo: String,
        val noc_photo_check: String,
        val num_plate_order: Any,
        val num_plate_recive: Any,
        val number_plat: String,
        val number_plat_check: String,
        val old_t_amount: String,
        val old_t_amount_check: String,
        val old_vehicle_photo: String,
        val old_vehicle_photo_check: String,
        val p_desc: String,
        val p_desc_check: String,
        val p_photo: String,
        val p_photo_back: String,
        val p_photo_back_check: String,
        val p_photo_check: String,
        val paper_expence: String,
        val paper_expence_check: String,
        val pinsurance: Any,
        val policy_amount: Any,
        val policy_number: Any,
        val product_name: String,
        val product_name_check: String,
        val r_e_name: String,
        val r_e_name_check: String,
        val rc_book_financial: Any,
        val rc_book_update_in: Any,
        val rc_update: Any,
        val rcbook_back: String,
        val rcbook_back_check: String,
        val recbook_photo: String,
        val recbook_photo_check: String,
        val recive_date: Any,
        val ref_name: String,
        val ref_name_check: String,
        val ref_no: String,
        val ref_no_check: String,
        val registration_num: Any,
        val rto: String,
        val rto_check: String,
        val rto_passing: String,
        val rto_passing_check: String,
        val rto_tax: String,
        val rto_tax_check: String,
        val sale_return: String,
        val sar_id_photo: String,
        val sar_id_photo_check: String,
        val sectiondate: String,
        val sectiondate_check: String,
        val sign_veri: String,
        val sign_veri_check: String,
        val skim: String,
        val skim_check: String,
        val sname: String,
        val sname_check: String,
        val stage: String,
        val stage_check: String,
        val state: String,
        val state_check: String,
        val tax: Any,
        val tax_amount: Any,
        val tehsill: String,
        val tehsill_check: String,
        val toolkit: String,
        val toolkit_check: String,
        val toplink: String,
        val toplink_check: String,
        val tyre: String,
        val tyre_check: String,
        val variants: Any,
        val variants_check: String,
        val village: String,
        val village_check: String,
        val whno: String,
        val whno_check: String,
        val cskim: String

        )


//booking_countModel
data class BookingCountModel(
        val cat: List<CatBookingModel>
)

data class CatBookingModel(
        val booking: String,
        val delivery: String,
        val docprint: String,
        val loan: String,
        val passing: String,
        val paymentpen: String,
        val fitment_count: String,
        val rc_fitment_customer: String,
        val rc_fitment_final: String
)


data class ClearPayPenData(
        val msg: String,
        val success: Boolean
)

data class InsentiveWalletModel(
        val `data`: List<DataInsensitive>
)

data class DataInsensitive(
        val Date: String,
        val Model: String,
        val amount: String,
        val employee: String,
        val id: String,
        val product: String,
        val cuname: String,
        val village: String,
        val withdrawel_date: String,
        val withdraw: String,
        val final_amount: String,
        val flag: String
)


data class WithdrawalWalletModel(
        val msg: String,
        val email: String,
        val success: Boolean
)


//workshop add
data class WorkShopAddProductModel(
        val detail: List<DetailWsProd>
)

data class DetailWsProd(
        val id: String,
        val partname: String
)


data class PartDataModel(
        val detail: List<DetailPartData>
)

data class DetailPartData(
        val Pno: String,
        val rate: String,
        val id: String
)


//WsEMpList
data class WsEmpListModel(
        val `data`: List<DataWsEmpList>
)

data class DataWsEmpList(
        val ename: String,
        val id: String
)

//Qty Available list
data class QtyAvailableWSModel(
        val detail: List<Any>,
        val msg: String,
        val success: Boolean
)


//Add WorkShop Phase1
data class PhaseOneWsAdd(
        val id: Int,
        val msg: String,
        val success: Boolean
)


//Add WS Second
data class SecondPhaseAddWsModel(
        val msg: String,
        val success: Boolean
)

//WS Phase API
data class WorkShoPhaseModel(
        val phase: String
)

//Ws Third
data class ThirdWSModel(
        val msg: String,
        val success: Boolean
)


//WS Forth
data class FourthWSModel(
        val msg: String,
        val id: String,
        val success: Boolean
)

data class WsPaymentPendingModel(
        val `data`: List<DataWsPayPen>,
        val error: Boolean,
        val message: String
)

data class DataWsPayPen(
        val b_type: String,
        val c_exit: String,
        val cash_amt: String,
        val cash_date: String,
        val cash_desc: String,
        val check_amt: String,
        val check_date: String,
        val check_desc: String,
        val city: String,
        val cname: String,
        val deal_price: String,
        val deal_price_word: String,
        val distric: String,
        val emp_id: String,
        val gst: String,
        val id: String,
        val l_emp: String,
        val laber_charge: String,
        val left_amt: String,
        val left_status: String,
        val mno: String,
        val mobileno: String,
        val model_name: String,
        val neft_rtgs_amt: String,
        val neft_rtgs_date: String,
        val neft_rtgs_desc: String,
        val p_type: String,
        val payment_type: String,
        val petrol_charge: String,
        val qty: String,
        val rate: String,
        val s_date: String,
        val s_emp: String,
        val state: String,
        val status: String,
        val tehsil: String,
        val totalprice: String,
        val type: String,
        val village: String,
        val whno: String
)


data class PaymentWSModel(
        val cat: List<CatPaymentWs>
)

data class CatPaymentWs(
        val cash_desc: String,
        val cashamount: String,
        val cashdate: String,
        val deal_price: String,
        val desc: String,
        val distric: String,
        val fname: String,
        val mobileno: String,
        val s_date: String,
        val tehsill: String,
        val tp: String,
        val village: String,

        val check_date: String,
        val check_amt: String,
        val check_desc: String,
        val neft_rtgs_date: String,
        val neft_rtgs_amt: String,
        val neft_rtgs_desc: String,

        val ref1: String,
        val refdate: String,
        val refdesc: String,

        val exdate1: String,
        val exdesc1: String,
        val exprice1: String,

        val exprice2: String,
        val exdate2: String,
        val exdesc2: String,

        val exprice3: String,
        val exdate3: String,
        val exdesc3: String
)


// Payment collection
data class PaymentCollectionBorrowListCount(
        val count: Int
)


//borrow One API Data
data class BorrowOneModel(
        val data: List<DataBorrowOne>,
        val error: Boolean,
        val message: String
)

data class DataBorrowOne(
        val Agent_free: String,
        val Policy_date: String,
        val add_date: String,
        val adhar_back: String,
        val adhar_back_check: String,
        val adhar_photo: String,
        val adhar_photo_check: String,
        val agent_fee: String,
        val agent_fee_check: String,
        val amount: String,
        val amount_check: String,
        val amount_word: String,
        val app_status: String,
        val approve_status: String,
        val atype: String,
        val atype_check: String,
        val b_date: String,
        val b_date_check: String,
        val b_p_photo: String,
        val b_p_photo_check: String,
        val b_pass_back: String,
        val b_pass_back_check: String,
        val b_photo: String,
        val b_photo_check: String,
        val b_type: String,
        val b_type_check: String,
        val battery: String,
        val battery_check: String,
        val bno: String,
        val bno_check: String,
        val booking_amt: String,
        val booking_amt_check: String,
        val booking_clear: String,
        val booking_clear_date: Any,
        val booking_exit: String,
        val booking_sub: String,
        val bumper: String,
        val bumper_check: String,
        val c_amount: String,
        val c_amount_check: String,
        val cash_amount: String,
        val cash_amount_check: String,
        val cash_date: String,
        val cash_date_check: String,
        val cash_description: String,
        val cash_description_check: String,
        val chasis_print: String,
        val chasis_print_check: String,
        val chasisno: String,
        val chasisno_check: String,
        val check1_photo: String,
        val check1_photo_check: String,
        val check_amt: String,
        val check_amt_check: String,
        val check_amt_word: String,
        val check_date: String,
        val check_date_check: String,
        val check_neft_rtgs: String,
        val check_neft_rtgs_check: String,
        val check_photo: String,
        val check_photo_check: String,
        val cibil_score: String,
        val cibil_score_check: String,
        val city: String,
        val city_check: String,
        val crtm: String,
        val deal_price: String,
        val deal_price_check: String,
        val deal_price_in_word: String,
        val deal_price_in_word_check: String,
        val delivery_date: String,
        val delivery_date_check: String,
        val delivry_photo: String,
        val delivry_photo_check: String,
        val description: String,
        val description_check: String,
        val distric: String,
        val distric_check: String,
        val dmodelname: String,
        val dmodelname_check: String,
        val drowbar: String,
        val drowbar_check: String,
        val ec_photo: String,
        val ec_photo_check: String,
        val ecard_photo: String,
        val ecard_photo_check: String,
        val edit_app: String,
        val elec_back: String,
        val elec_back_check: String,
        val election_back: String,
        val election_back_check: String,
        val emp: String,
        val emp_check: String,
        val emp_id: String,
        val emp_id_check: String,
        val empid: String,
        val engineno: String,
        val engineno_check: String,
        val expriy_date: String,
        val fi_date: String,
        val fi_date_check: String,
        val filter: Any,
        val final_amt: String,
        val finance_from: String,
        val finance_from_check: String,
        val finish: String,
        val fitment: String,
        val fname: String,
        val fname_check: String,
        val gst: String,
        val gst_check: String,
        val hitch: String,
        val hitch_check: String,
        val hood: String,
        val hood_check: String,
        val id: String,
        val insurance: String,
        val insurance_check: String,
        val insurance_com_name: String,
        val l_c_photo: String,
        val l_c_photo_check: String,
        val l_sec_amt: String,
        val l_sec_amt_check: String,
        val land_details: String,
        val land_details_check: String,
        val lb_pb_photo: String,
        val lb_pb_photo_check: String,
        val lloan_charge: String,
        val lloan_charge_check: String,
        val lname: Any,
        val lname_check: String,
        val loan_amount: String,
        val loan_amount_check: String,
        val loan_charge: String,
        val loan_charge_check: String,
        val loans: String,
        val m_year: String,
        val m_year_check: String,
        val make: String,
        val make_check: String,
        val mobileno: String,
        val mobileno_check: String,
        val model: String,
        val model_check: String,
        val model_name: String,
        val model_name_check: String,
        val neft_rtgs_amt: String,
        val neft_rtgs_amt_check: String,
        val neft_rtgs_amt_word: String,
        val neft_rtgs_date: String,
        val neft_rtgs_date_check: String,
        val neft_rtgs_photo: String,
        val neft_rtgs_photo_check: String,
        val noc: String,
        val noc_check: String,
        val noc_photo: String,
        val noc_photo_check: String,
        val num_plate_order: String,
        val num_plate_recive: String,
        val number_plat: String,
        val number_plat_check: String,
        val old_t_amount: String,
        val old_t_amount_check: String,
        val old_t_amt_word: String,
        val old_vehicle_photo: String,
        val old_vehicle_photo_check: String,
        val p_desc: String,
        val p_desc_check: String,
        val p_photo: String,
        val p_photo_back: String,
        val p_photo_back_check: String,
        val p_photo_check: String,
        val paper_expence: String,
        val paper_expence_check: String,
        val pinsurance: String,
        val policy_amount: String,
        val policy_number: String,
        val product_name: String,
        val product_name_check: String,
        val r_e_name: String,
        val r_e_name_check: String,
        val rc_book_financial: String,
        val rc_book_update_in: String,
        val rc_update: String,
        val rcbook_back: String,
        val rcbook_back_check: String,
        val recbook_photo: String,
        val recbook_photo_check: String,
        val recive_date: String,
        val ref_name: String,
        val ref_name_check: String,
        val ref_no: String,
        val ref_no_check: String,
        val registration_num: String,
        val rto: String,
        val rto_check: String,
        val rto_passing: String,
        val rto_passing_check: String,
        val rto_tax: String,
        val rto_tax_check: String,
        val sale_return: String,
        val sar_id_photo: String,
        val sar_id_photo_check: String,
        val sectiondate: String,
        val sectiondate_check: String,
        val sign_veri: String,
        val sign_veri_check: String,
        val skim: String,
        val skim_check: String,
        val sname: String,
        val sname_check: String,
        val stage: String,
        val stage_check: String,
        val state: String,
        val state_check: String,
        val tax: String,
        val tax_amount: String,
        val tehsill: String,
        val tehsill_check: String,
        val toolkit: String,
        val toolkit_check: String,
        val toplink: String,
        val toplink_check: String,
        val total_amt: String,
        val tyre: String,
        val tyre_check: String,
        val variants: String,
        val variants_check: String,
        val village: String,
        val village_check: String,
        val whno: String,
        val whno_check: String,
        val n_date: String,
        val v_reason: String,
        val customer_name: String

)


//Borrow Two
data class BorrowTwoModel(
        val data: List<DataBorrowTwo>,
        val error: Boolean,
        val message: String
)

data class DataBorrowTwo(
        val b_type: String,
        val c_exit: String,
        val cash_amt: String,
        val cash_date: String,
        val cash_desc: String,
        val check_amt: String,
        val check_date: String,
        val check_desc: String,
        val city: String,
        val cname: String,
        val deal_price: String,
        val deal_price_word: String,
        val distric: String,
        val emp_id: String,
        val gst: String,
        val id: String,
        val l_emp: String,
        val laber_charge: String,
        val left_amt: String,
        val left_status: String,
        val mno: String,
        val mobileno: String,
        val model_name: String,
        val neft_rtgs_amt: String,
        val neft_rtgs_date: String,
        val neft_rtgs_desc: String,
        val p_type: String,
        val payment_type: String,
        val petrol_charge: String,
        val qty: String,
        val rate: String,
        val s_date: String,
        val s_emp: String,
        val state: String,
        val status: String,
        val tehsil: String,
        val totalprice: String,
        val type: String,
        val village: String,
        val whno: String,
        val n_date: String,
        val v_reason: String,
        val vilage_wise_emp: String

)


//Borrow One Ledger APi Model
data class BorrowOneLedgerModel(
        val cat: List<CatBorrowOne>
)

data class CatBorrowOne(
        val Old_Vehicle_amount: String,
        val add_date: String,
        val battery: String,
        val bno: String,
        val camt: String,
        val cashamount: String,
        val cashamt: String,
        val cashdate: String,
        val cashdesc: String,
        val chasisno: String,
        val cpdelivery_date: String,
        val cudesc: String,
        val deal_price: String,
        val delivery_date: String,
        val desc: String,
        val distric: String,
        val dmodelname: String,
        val engineno: String,
        val exdate1: String,
        val exdesc1: String,
        val exprice1: String,
        val finance_from: String,
        val fname: String,
        val loanamt: String,
        val loandate: String,
        val loandesc: String,
        val mobileno: String,
        val ptypef: String,
        val ptypeo: String,
        val tehsill: String,
        val tp: Int,
        val tyre: String,
        val variants: String,
        val village: String,

        val uprice: String,
        val udate: String,
        val udesc: String,

        val uprice1: String,
        val udate1: String,
        val udesc1: String,

        val exprice2: String,
        val exdate2: String,
        val exdesc2: String,

        val exprice3: String,
        val exdate3: String,
        val exdesc3: String,

        val ptypes: String,
        val ptypet: String,

        val Chequeamount: String,
        val NEFT_RTGSamount: String,

        val ref1: String,
        val refdate: String,
        val refdesc: String,

        val odesc: String,
        val other_amt: String,
        val datee: String,


        val work_deal_price: String,
        val work_date: String,
        val work_desc: String,

        val work_com_price: String,
        val work_com_date: String,
        val work_com_desc: String,

        val work_de_price: String,
        val work_de_date: String,
        val work_de_desc: String,

        val work_cu_cash_price: String,
        val work_cu_cash_date: String,
        val work_cu_cash_desc: String,

        val work_cu_che_price: String,
        val work_cu_che_date: String,
        val work_cu_che_desc: String,

        val work_cu_nert_price: String,
        val work_cu_nert_date: String,
        val work_cu_nert_desc: String,

        val dexdate1: String,
        val dexdate2: String,
        val dexdate3: String,
        val dexdesc1: String,
        val dexdesc2: String,
        val dexdesc3: String,
        val dexprice1: Int,
        val dexprice2: Int,
        val dexprice3: Int
)

data class BorrowLedgerTwoModel(
    val cat: List<CatBorrowLedgerTwo>
)

data class CatBorrowLedgerTwo(
    val Old_Vehicle_amount: String,
    val add_date: String,
    val battery: Any,
    val bno: String,
    val camt: Int,
    val chasisno: String,
    val cpdelivery_date: String,
    val cudesc: String,
    val datee: String,
    val deal_price: Int,
    val delivery_date: String,
    val desc: String,
    val distric: String,
    val dmodelname: String,
    val engineno: String,
    val exdate1: String,
    val exdesc1: String,
    val exprice1: Int,
    val finance_from: String,
    val fname: String,
    val mobileno: String,
    val odesc: String,
    val other_amt: Int,
    val ptypeo: String,
    val tehsill: String,
    val tp: Int,
    val tyre: String,
    val variants: String,
    val village: String,
    val work_com_date: String,
    val work_com_desc: String,
    val work_com_price: Int,
    val work_cu_cash_date: String,
    val work_cu_cash_desc: String,
    val work_cu_cash_price: Int,
    val work_cu_che_date: String,
    val work_cu_che_desc: String,
    val work_cu_che_price: Int,
    val work_cu_nert_date: String,
    val work_cu_nert_desc: String,
    val work_cu_nert_price: Int,
    val work_date: String,
    val work_de_date: String,
    val work_de_desc: String,
    val work_de_price: Int,
    val work_deal_price: Int,
    val work_desc: String,

    val s_date: String,

    val cash_desc: String,
    val cashamount: String,
    val cashdate: String,

    val check_date: String,
    val check_amt: String,
    val check_desc: String,
    val neft_rtgs_date: String,
    val neft_rtgs_amt: String,
    val neft_rtgs_desc: String,

    val ref1: String,
    val refdate: String,
    val refdesc: String,


    val exprice2: String,
    val exdate2: String,
    val exdesc2: String,

    val exprice3: String,
    val exdate3: String,
    val exdesc3: String
)

//Do it inquiry model
data class InquiryDataBankModel(
        val `data`: List<DataInquiryDataBank>
)

data class DataInquiryDataBank(
        val cat_id: String,
        val cat_list: String,
        val make_name: String,
        val model_name: String,
        val model_y: String,
        val sor_inq: String,
        val status: String
)


//Inquiry General Visit
data class InquiryGeneralVisitMainModel(
        val cat: List<CatInquiryGen>
)

data class CatInquiryGen(
        val cat_id: String,
        val cat_list: String,
        val count: String
)
data class inquiryGenDetailsModel1(
        val cat: ArrayList<CatInquiryGenDetail1>
)

data class CatInquiryGenDetail1(
        val autoid: String,
        val cat_id: String,
        val cat_name: String,
        val city: String,
        val desc: String,
        val distric: String,
        val employee_name: String,
        val fname: String,
        val lname: String,
        val moblino: String,
        val state: String,
        val tehsil: String,
        val vilage: String,
        val whatsappno: String,
        val follow_up_type: String,
        val inq_new: String,
        val inq_overdue: String,
        val sid: String,
        val sor_of_inq: String,
        val model: String,
        val days: String,
        val next_date: String,
        val type_inq: String,
        val vemp: String
)

data class inquiryGenDetailsModel(
        val cat: ArrayList<CatInquiryGenDetail>
)

data class CatInquiryGenDetail(
        val autoid: String,
        val cat_id: String,
        val cat_name: String,
        val city: String,
        val desc: String,
        val distric: String,
        val employee_name: String,
        val fname: String,
        val lname: String,
        val moblino: String,
        val state: String,
        val tehsil: String,
        val vilage: String,
        val whatsappno: String,
        val follow_up_type: String,
        val inq_new: String,
        val inq_overdue: String,
        val inq_id: String,
        val sid: String,
        val sor_of_inq: String,
        val model: String,
        val days: String,
        val next_date: String,
        val type_inq: String,
        val vemp: String
)


data class InqAddGenVisitModel(
    val msg: String,
    val success: Boolean
)

//Inquiry Monthly data model
data class InqMonthlyModel(
        val detail: List<DetailInqMonth>
)

data class DetailInqMonth(
        val count: Int,
        val id: String,
        val month: String
)


//Inquiry Weekly Data Model
data class WeeklyInquiryOneModel(
        val detail: List<DetailWeeklyInq>
)

data class DetailWeeklyInq(
        val count: Int,
        val day: String,
        val id: String
)


//Daily Inquiry data Model
data class DailyInqOneModel(
        val detail: List<DetailDailyInq>
)

data class DetailDailyInq(
        val count: Int,
        val id: String
)

data class InquiryAllDatWeekDayMonthModel(
        val cat: List<CatInq>
)

data class CatInq(
        val autoid: String,
        val booking: String,
        val booking_amt: String,
        val cat_name: String,
        val city: String,
        val delivry: String,
        val desc: String,
        val distric: String,
        val employee_name: String,
        val fname: String,
        val id: String,
        val moblino: String,
        val model_name: String,
        val reason: String,
        val sell_lost: String,
        val tehsil: String,
        val tractorname: String,
        val vdate: String,
        val follow_up_type: String,
        val inq_new: String,
        val inq_overdue: String,
        val type_inq: String,
        val sor_of_inq: String,
        val model: String,
        val added: String,
        val vilage: String,
        val whatsappno: String,
        val cat_id: String,
        val inq_type: String,
        val vemp: String
)


//visit inquiry model
data class VisitInquiryModel(
        val cat: List<CatVisitInq>
)

data class CatVisitInq(
        val booking: String,
        val booking_amt: String,
        val cat_id: String,
        val cat_name: String,
        val city: String,
        val cudate: String,
        val delivry: String,
        val desc: String,
        val distric: String,
        val employee_name: String,
        val fname: String,
        val id: String,
        val lname: String,
        val moblino: String,
        val model_name: String,
        val reason: String,
        val sell_lost: String,
        val sell_model: String,
        val state: String,
        val tehsil: String,
        val vdate: String,
        val vilage: String,
        val whatsappno: String,
        val follow_up_status: String,
        val followstatus: String
)


//Add Inquiry Model
data class AddInquiryModel(
        val msg: String,
        val success: Boolean
)


//Wallet OvertTime Model
data class OverTimeWalletModel(
        val overtime: List<Overtime>
)

data class Overtime(
        val cdate: String,
        val checkin: String,
        val checkout: String,
        val payout: Int,
        val workinghour: String
)


//Check Ot Phase
data class CheckOtPhaseModel(
        val phase: String
)


//Traveling Start Model
data class StartTravellingModel(
        val id: Int,
        val msg: String,
        val phase: Int,
        val success: Boolean
)


//End Traveling Model
data class EndTravelingModel(
        val msg: String,
        val success: Boolean
)


//Phase Traveling Model
data class PhaseTravelingModel(
        val phase: Any
)

//Traveling data Display Model
data class TravelingDataDisplayModel(
        val travaling: List<Travaling>
)

data class Travaling(
        val cdate: String,
        val ch_km: String,
        val checkin: String,
        val checkout: String,
        val chout_km: String,
        val payout: Double,
        val totalkm: Int
)


//Add Customer Model
data class AddCustomerModel(
        val msg: String,
        val success: Boolean
)


//Add Customer Mobile through dta get Model
data class MobileDataAddCustomerProfileModel(
        val cat: List<CatAddCustomer>
)

data class CatAddCustomer(
        val autoid: String,
        val cat_id: String,
        val cat_name: String,
        val city: String,
        val cityid: String,
        val desc: String,
        val distric: String,
        val districid: String,
        val eid: String,
        val employee_name: String,
        val fname: String,
        val lname: String,
        val moblino: String,
        val state: String,
        val stateid: String,
        val tehsil: String,
        val tehsilid: String,
        val vilage: String,
        val vilageid: String,
        val whatsappno: String
)

//View Customerprofile Display
data class ViewCustomerProfileDataModel(
    val customer_profile: ArrayList<CustomerProfile>
)

data class CustomerProfile(
    val cat_id: String,
    val cat_name: String,
    val city: String,
    val cityid: String,
    val disid: String,
    val distric: String,
    val empid: String,
    val employee_name: String,
    val fname: String,
    val lname: String,
    val moblino: String,
    val note: String,
    val state: String,
    val stateid: String,
    val tehsil: String,
    val tehsilid: String,
    val vilage: String,
    val vilageid: String,
    val whatsappno: String,
    val id: String,
    val addemp: String,
    val add_date: String,
    val drop: String,
    val approve_delete: String,
    val maker: String,
    val model_name: String,
    val manufacture_year: String,
    val registration_no: String,
    val selects: Boolean

)


//View Inquiry Employee Model
data class ViewInquiryEmployeeListModel(
        val `data`: List<DataEmployeeList>
)

data class DataEmployeeList(
        val id: String,
        val state: String
)


//View Inquiry General List Category
data class ViewInqGenListCateModel(
        val cat: List<CatViewInqGenCat>
)

data class CatViewInqGenCat(
        val cat_id: String,
        val cat_list: String,
        val count: String
)


//View Inquiry General List Category
data class ViewInqUserGenListCateModel(
        val data: List<CatViewUserInqGenCat>
)

data class CatViewUserInqGenCat(
        val id: String,
        val emp_name: String,

)


//View Inquiry General Data
data class GeneralCatDataViewInqModel(
        val cat: List<CatGenCatDataVI>
)

data class CatGenCatDataVI(

        val autoid: String,
        val cat_id: String,
        val cat_name: String,
        val city: String,
        val desc: String,
        val distric: String,
        val employee_name: String,
        val fname: String,
        val lname: String,
        val moblino: String,
        val state: String,
        val tehsil: String,
        val vilage: String,
        val whatsappno: String,
        val follow_up_type: String,
        val inq_new: String,
        val inq_overdue: String,
        val sid: String,
        val sor_of_inq: String,
        val model: String,
        val days: String,
        val next_date: String,
        val type_inq: String,
        val vemp: String
)


//View Inquiry Form Model
data class ViewInqFormAddModel(
        val msg: String,
        val success: Boolean
)


//View Inquiry MOnthly Main Model
data class ViewInqMonthlyOneModel(
        val detail: List<DetailViewInqMonth>
)

data class DetailViewInqMonth(
        val count: Int,
        val id: String,
        val month: String
)

//View Inquiry Monthly data Display
data class MonthlyDataViewInqModel(
        val cat: List<CatMonthlyDataDisplayVI>
)

data class CatMonthlyDataDisplayVI(
        val autoid: String,
        val booking: String,
        val booking_amt: String,
        val cat_name: String,
        val city: String,
        val delivry: String,
        val desc: String,
        val distric: String,
        val employee_name: String,
        val fname: String,
        val id: String,
        val moblino: String,
        val model_name: String,
        val reason: String,
        val sell_lost: String,
        val tehsil: String,
        val tractorname: String,
        val vdate: String,
        val follow_up_type: String,
        val inq_new: String,
        val inq_overdue: String,
        val type_inq: String,
        val sor_of_inq: String,
        val model: String,
        val added: String,
        val vilage: String,
        val whatsappno: String,
        val cat_id: String,
        val inq_type: String,
        val vemp: String
)


//Weekly View Inq Model
data class WeeklyViewInqModel(
        val detail: List<DetailWeeklyViewInq>
)

data class DetailWeeklyViewInq(
        val count: Int,
        val day: String,
        val id: String
)


//Daily View Inq Model
data class DailyViewInqModel(
        val detail: List<DetailDailyVI>
)

data class DetailDailyVI(
        val count: Int,
        val id: String
)


// Daily Monthly Weekly Model
data class DailyMonthWeekVIModel(
        val msg: String,
        val success: Boolean
)


//WorkShop Manager Data Display Model

data class WsManagerDataDisplayModel(
        val `data`: List<DataWSManager>,
        val error: Boolean,
        val message: String
)

data class DataWSManager(
        val b_type: String,
        val c_exit: String,
        val cash_amt: String,
        val cash_date: String,
        val cash_desc: String,
        val check_amt: String,
        val check_date: String,
        val check_desc: String,
        val city: String,
        val cname: String,
        val deal_price: String,
        val deal_price_word: String,
        val distric: String,
        val emp_id: String,
        val entry_date: String,
        val filter: Any,
        val gst: String,
        val id: String,
        val index_val: Any,
        val l_emp: String,
        val laber_charge: String,
        val left_amt: String,
        val left_status: String,
        val mno: String,
        val mobileno: String,
        val model_name: String,
        val n_date: Any,
        val neft_rtgs_amt: String,
        val neft_rtgs_date: String,
        val neft_rtgs_desc: String,
        val nsub_val: Any,
        val p_type: String,
        val payment_type: String,
        val petrol_charge: String,
        val qty: String,
        val rate: String,
        val rem_val: String,
        val s_date: String,
        val s_emp: String,
        val state: String,
        val status: String,
        val sub_val: Any,
        val tehsil: String,
        val totalprice: String,
        val type: String,
        val v_reason: Any,
        val village: String,
        val whno: String,
        val works_ser: String,
        val engine_no: String,
        val chasis_no: String,
        val macanic_name: String
)


//WS Manager Edit Model
data class WSManagerEditModel(
    val msg: String,
    val success: Boolean
)



//Invoice Data display model
data class InvoiceDataDisplayModel(
    val workshop: List<WorkshopInvoice>
)

data class WorkshopInvoice(
    val cname: String,
    val deal_price: String,
    val id: String,
    val macanic: String,
    val mobileno: String,
    val type: String,
    val vilage: String,
    val path: String
)



//Invoice View

data class InvoiceViewModel(
    val `data`: List<DataViewInvoice>,
    val error: Boolean,
    val message: String
)

data class DataViewInvoice(
    val b_type: String,
    val c_exit: String,
    val cash_amt: String,
    val cash_date: String,
    val cash_desc: String,
    val chasis_no: String,
    val check_amt: String,
    val check_date: String,
    val check_desc: String,
    val city: String,
    val cname: String,
    val deal_price: String,
    val deal_price_word: String,
    val distric: String,
    val emp_id: String,
    val engine_no: String,
    val entry_date: String,
    val filter: Any,
    val gst: String,
    val id: String,
    val index_val: Any,
    val l_emp: String,
    val laber_charge: String,
    val left_amt: String,
    val left_status: String,
    val mno: String,
    val mobileno: String,
    val model_name: String,
    val n_date: Any,
    val neft_rtgs_amt: String,
    val neft_rtgs_date: String,
    val neft_rtgs_desc: String,
    val nsub_val: Any,
    val p_type: String,
    val payment_type: String,
    val petrol_charge: String,
    val qty: String,
    val rate: String,
    val recored_manage: String,
    val rem_val: String,
    val s_date: String,
    val s_emp: String,
    val state: String,
    val status: String,
    val sub_val: Any,
    val tehsil: String,
    val totalprice: String,
    val type: String,
    val v_reason: Any,
    val village: String,
    val whno: String,
    val works_ser: String,
    val macanic_name: String
)


//WS GEneral Visit
data class WSGeneralModel(
    val `data`: List<DataGenaralWS>
)

data class DataGenaralWS(
    val cuname: String,
    val id: String,
    val mobileno: String,
    val next_date: String,
    val remark: String,
    val village: String,
    val works_ser: String
)

//Work Shop Monthly Model
data class MonthlyOneWorkShopModel(
    val detail: List<DetailMotnhWSOne>
)

data class DetailMotnhWSOne(
    val count: Int,
    val id: String,
    val month: String
)


//WrokShop Weekly Model
data class WeekOneWsModel(
    val detail: List<DetailWeekWS>
)

data class DetailWeekWS(
    val count: Int,
    val day: String,
    val id: String
)


//WorkShop Daily Model
data class DailyWsOneModel(
    val detail: List<DetailDailyWs>
)

data class DetailDailyWs(
    val count: Int,
    val id: String
)


//WorkShop Month Week daily data Display

data class WsMonthWeekDailyModel(
    val cat: List<CatWsMonthWeekDay>
)

data class CatWsMonthWeekDay(
    val cuname: String,
    val id: String,
    val mobileno: String,
    val next_date: String,
    val remark: String,
    val village: String,
    val works_ser: String,
    val spart_id: String
)


//MOnth Week Day MOdel WS
data class WSMonthWeekDailyEditFormModel(
    val msg: String,
    val success: Boolean
)


//Feedback Call Model
data class FeedbackCallWSModel(
    val `data`: List<DataFeedBack>
)

data class DataFeedBack(
    val cuname: String,
    val id: String,
    val mobileno: String,
    val village: String,
    val works_ser: String,
    val macanic: String
)

//WorkShop Data Number
data class WorkshopNumberAllModel(
    val cat: List<CatWorkshoNUmber>
)

data class CatWorkshoNUmber(
    val dailymechanicreport: String,
    val daliy: String,
    val genral: String,
    val monthly: String,
    val paymentpending: String,
    val weekly: String,
    val feedbackcall: String,
    val complain: String
)

//WorkShop Feedback Call
data class WorkshopFeedbackCallremoveModel(
    val msg: String,
    val success: Boolean
)

//Remove Call Workshop Model
data class DeleteCallWorkshopModel(
    val msg: String,
    val success: Boolean
)


//End_Task Dashboard model

data class EndTaskModel(
    val msg: String,
    val success: Boolean
)




//ViewInquiry Model
data class VisitVIModel(
    val cat: List<CatVisitVI>
)

data class CatVisitVI(
    val addgcust: String,
    val amount: String,
    val booking: String,
    val booking_amt: String,
    val cat_id: String,
    val cat_name: String,
    val city: String,
    val cudate: String,
    val delivry: String,
    val desc: String,
    val distric: String,
    val employee_name: String,
    val fname: String,
    val id: String,
    val lname: String,
    val moblino: String,
    val model_name: String,
    val payment_collection: String,
    val reason: String,
    val sell_lost: String,
    val sell_model: String,
    val state: String,
    val tehsil: String,
    val vdate: String,
    val vilage: Any,
    val whatsappno: String,
    val follow_up_status: String
)



//insert payment collection model
data class insertPaymentCollectionModel(
    val msg: String,
    val success: Boolean
)


data class PaymentCelClearModel(
    val msg: String,
    val success: Boolean
)

data class HomeItemodel(
        val cat: List<HomeItems>
)

data class HomeItems(
        val item_name:String,
        val item_name2:String,
        val item_img:Int,
        val id:Int,
        val flag:Int
)


//Category Permission View
data class CatPerDetailModel(
    val cat: List<CatCarPerDetial>
)

data class CatCarPerDetial(
    val cat_id: String,
    val cat_name: String,
    val chasisno: Any,
    val city: String,
    val desc: String,
    val distric: String,
    val employee_name: String,
    val engineno: Any,
    val fname: String,
    val id: String,
    val image1: Any,
    val image2: Any,
    val lname: String,
    val mfgy: Any,
    val moblino: String,
    val model_t_name: Any,
    val path: String,
    val pelough: Any,
    val rotavator: Any,
    val speeddrel: Any,
    val state: String,
    val tehsil: String,
    val tractor: Any,
    val vilage: String,
    val whatsappno: String
)




//Edit Profile Model
data class EditProfileModel(
    val message: String
)

data class ActivityReviewData (

        @SerializedName("data"    ) var data    : ArrayList<ActivityData> = arrayListOf(),
        @SerializedName("error"   ) var error   : Boolean?        = null,
        @SerializedName("message" ) var message : String?         = null

)

data class ActivityData (

        @SerializedName("id"                           ) var id                        : String? = null,
        @SerializedName("cate_type"                    ) var cateType                  : String? = null,
        @SerializedName("login_id"                     ) var loginId                   : String? = null,
        @SerializedName("cat_id"                       ) var catId                     : String? = null,
        @SerializedName("fname"                        ) var fname                     : String? = null,
        @SerializedName("lname"                        ) var lname                     : String? = null,
        @SerializedName("state"                        ) var state                     : String? = null,
        @SerializedName("city"                         ) var city                      : String? = null,
        @SerializedName("distric"                      ) var distric                   : String? = null,
        @SerializedName("tehsil"                       ) var tehsil                    : String? = null,
        @SerializedName("vilage"                       ) var vilage                    : String? = null,
        @SerializedName("moblino"                      ) var moblino                   : String? = null,
        @SerializedName("whatsappno"                   ) var whatsappno                : String? = null,
        @SerializedName("other_mobile"                 ) var otherMobile               : String? = null,
        @SerializedName("emp"                          ) var emp                       : String? = null,
        @SerializedName("description"                  ) var description               : String? = null,
        @SerializedName("filter"                       ) var filter                    : String? = null,
        @SerializedName("status"                       ) var status                    : String? = null,
        @SerializedName("otp_verified"                 ) var otpVerified               : String? = null,
        @SerializedName("cu_date"                      ) var cuDate                    : String? = null,
        @SerializedName("empid"                        ) var empid                     : String? = null,
        @SerializedName("type"                         ) var type                      : String? = null,
        @SerializedName("task_type"                    ) var taskType                  : String? = null,
        @SerializedName("cdate"                        ) var cdate                     : String? = null,
        @SerializedName("re_status"                    ) var reStatus                  : String? = null,
        @SerializedName("re_date"                      ) var reDate                    : String? = null,
        @SerializedName("re_id"                        ) var reId                      : String? = null,
        @SerializedName("re_empid"                     ) var reEmpid                   : String? = null,
        @SerializedName("repeat_status"                ) var repeatStatus              : String? = null,
        @SerializedName("new_id"                       ) var newId                     : String? = null,
        @SerializedName("next_date"                    ) var nextDate                  : String? = null,
        @SerializedName("type_inq"                     ) var typeInq                   : String? = null,
        @SerializedName("inq_new"                      ) var inqNew                    : String? = null,
        @SerializedName("make_name"                    ) var makeName                  : String? = null,
        @SerializedName("model"                        ) var model                     : String? = null,
        @SerializedName("sor_of_inq"                   ) var sorOfInq                  : String? = null,
        @SerializedName("model_y"                      ) var modelY                    : String? = null,
        @SerializedName("follow_up_type"               ) var followUpType              : String? = null,
        @SerializedName("drop_status"                  ) var dropStatus                : String? = null,
        @SerializedName("drop_emp"                     ) var dropEmp                   : String? = null,
        @SerializedName("drop_date"                    ) var dropDate                  : String? = null,
        @SerializedName("tractor"                      ) var tractor                   : String? = null,
        @SerializedName("model_t_name"                 ) var modelTName                : String? = null,
        @SerializedName("image1"                       ) var image1                    : String? = null,
        @SerializedName("image2"                       ) var image2                    : String? = null,
        @SerializedName("mfgy"                         ) var mfgy                      : String? = null,
        @SerializedName("engineno"                     ) var engineno                  : String? = null,
        @SerializedName("chasisno"                     ) var chasisno                  : String? = null,
        @SerializedName("rotavator"                    ) var rotavator                 : String? = null,
        @SerializedName("speeddrel"                    ) var speeddrel                 : String? = null,
        @SerializedName("pelough"                      ) var pelough                   : String? = null,
        @SerializedName("edit_app"                     ) var editApp                   : String? = null,
        @SerializedName("cu_profile_type"              ) var cuProfileType             : String? = null,
        @SerializedName("cu_profile_status"            ) var cuProfileStatus           : String? = null,
        @SerializedName("cu_profile_date"              ) var cuProfileDate             : String? = null,
        @SerializedName("cu_profile_emp"               ) var cuProfileEmp              : String? = null,
        @SerializedName("mp_status"                    ) var mpStatus                  : String? = null,
        @SerializedName("mp_date"                      ) var mpDate                    : String? = null,
        @SerializedName("mp_type"                      ) var mpType                    : String? = null,
        @SerializedName("mp_emp"                       ) var mpEmp                     : String? = null,
        @SerializedName("approve_new_tracking_inquiry" ) var approveNewTrackingInquiry : String? = null,
        @SerializedName("oldtractor"                   ) var oldtractor                : String? = null,
        @SerializedName("modelyear"                    ) var modelyear                 : String? = null,
        @SerializedName("mini_big"                     ) var miniBig                   : String? = null,
        @SerializedName("interested"                   ) var interested                : String? = null,
        @SerializedName("remarks"                      ) var remarks                   : String? = null,
        @SerializedName("whenbuy"                      ) var whenbuy                   : String? = null,
        @SerializedName("offer"                        ) var offer                     : String? = null,
        @SerializedName("talkus"                       ) var talkus                    : String? = null,
        @SerializedName("datavarify"                   ) var datavarify                : String? = null,
        @SerializedName("remarks2"                     ) var remarks2                  : String? = null,
        @SerializedName("w_reason"                     ) var wReason                   : String? = null,
        @SerializedName("new_reason"                   ) var newReason                 : String? = null,
        @SerializedName("activity_reason"              ) var activityReason            : String? = null,
        @SerializedName("reason_date"                  ) var reasonDate                : String? = null,
        @SerializedName("new_reason_date"              ) var newReasonDate             : String? = null,
        @SerializedName("activity_reason_date"         ) var activityReasonDate        : String? = null,
        @SerializedName("filter_type"                  ) var filterType                : String? = null,
        @SerializedName("filter_type2"                 ) var filterType2               : String? = null,
        @SerializedName("filter_type3"                 ) var filterType3               : String? = null,
        val phn_flag: String,
        val whatsapp_flag: String,
        val sms_flag: String,
        val phn_review: String,
        val sms_review: String,
        val whatsapp_review: String,
        val skip_flag: String,
        val submit_done: String

)


data class ViewCustomerProfileEditModel(
    val customer_profile: List<CustomerProfileEdit>
)

data class CustomerProfileEdit(
    val cat_id: String,
    val cat_name: String,
    val cate_type: String,
    val chasisno: String,
    val city: String,
    val cityid: String,
    val disid: String,
    val distric: String,
    val empid: String,
    val employee_name: String,
    val engineno: String,
    val fname: String,
    val id: String,
    val image1: String,
    val image2: String,
    val lname: String,
    val mfgy: String,
    val moblino: String,
    val model_t_name: String,
    val note: String,
    val path: String,
    val pelough: String,
    val rotavator: String,
    val speeddrel: String,
    val state: String,
    val stateid: String,
    val tehsil: String,
    val tehsilid: String,
    val tractor: String,
    val vilage: String,
    val vilageid: String,
    val whatsappno: String,
    val desc: String
)


//CustomerProfile Edit Employee list Model

data class CUEditProfileEmpListModel(
    val `data`: List<DataEmpList>
)

data class DataEmpList(
    val cat_id: String,
    val cat_list: String,
    val status: String
)

//View Profile Main Category Detail Model
data class MyProfileCategoryModel(
    val cat: List<CatMyProfile>
)

data class CatMyProfile(
    val cat_id: String,
    val cat_list: String,
    val count: String
)

//VIew Profile Details Model

data class ViewProfileCategoryDetailsModel(
    val cat: List<CatViewProfDetail>
)

data class CatViewProfDetail(
    val cat_id: String,
    val cat_name: Any,
    val city: String,
    val desc: String,
    val distric: String,
    val employee_name: String,
    val fname: String,
    val lname: String,
    val moblino: String,
    val state: String,
    val tehsil: String,
    val vilage: String,
    val whatsappno: String,
    val autoid: String,
    val eid: String
)


//Display Score Board Model
data class ScoreBoardDisplayModel(
    val cat: List<CatScoreBoard>
)

data class CatScoreBoard(
        val databank: String,
        val databankcount: String,
        val databankpoint: Double,
        val totalcount: Double,
        val totalpoint: Double
)




//customer prfofile csw model
data class CustomerProfileCSW_Model(
    val msg: String,
    val success: Boolean
)



data class myInqCswModel(
    val msg: String,
    val success: Boolean
)


data class MyProfileCSWModel(
    val msg: String,
    val success: Boolean
)


data class BookingUpload_PaymentPendingCSW_Model(
    val msg: String,
    val success: Boolean
)


data class PaymentCollection_CSWModel(
    val msg: String,
    val success: Boolean
)

data class TrueValueBrandModel(
    val data: List<DataBrand>
)

data class DataBrand(
    val brand: String,
    val id: String
)

data class TrueValueBrandModel_Model(
    val `data`: List<DataBrandModel>
)

data class DataBrandModel(
    val id: String,
    val model_id: String,
    val model_name: String
)

data class TrueValueFormOneModel(
    val id: Int,
    val msg: String,
    val success: Boolean
)


/*data class TvAddFormTwoModel(
    val msg: String,
    val success: Boolean
)*/
data class TvAddFormTwoModel(
    val msg: String,
    val price: String,
    val success: Boolean
)



data class MyProfileVillageWiseEmpModel(
    val cat: List<CatMyProVillage>
)

data class CatMyProVillage(
    val auto_id: String,
    val cat_id: String,
    val cat_name: String,
    val city: String,
    val desc: String,
    val distric: String,
    val employee_name: String,
    val fname: String,
    val lname: String,
    val moblino: String,
    val state: String,
    val tehsil: String,
    val vilage: String,
    val whatsappno: String,
    val eid: String
)


data class MyProfileAddFormModel(
    val msg: String,
    val success: Boolean
)


data class MyProfileMonthModel(
    val detail: List<DetailMtProfileMonth>
)

data class DetailMtProfileMonth(
    val count: Int,
    val id: String,
    val month: String
)

data class WeeklyMyProfileModel(
    val detail: List<DetailWeeklyMyProfile>
)

data class DetailWeeklyMyProfile(
    val count: Int,
    val day: String,
    val id: String
)

data class DayMyProfileModel(
    val detail: List<DetailDailyMyProfile>
)

data class DetailDailyMyProfile(
    val count: Int,
    val id: String
)


data class MyProfileSecondAllDetailModel(
    val cat: List<CatSecondMyProfile>
)

data class CatSecondMyProfile(
    val autoid: String,
    val booking: Any,
    val booking_amt: Any,
    val cat_id: String,
    val cat_name: String,
    val city: String,
    val delivry: Any,
    val desc: String,
    val distric: String,
    val employee_name: String,
    val fname: String,
    val id: String,
    val inq_type: Any,
    val moblino: String,
    val model_name: Any,
    val reason: String,
    val sell_lost: Any,
    val state: String,
    val tehsil: String,
    val tractorname: Any,
    val vdate: String,
    val vemp: String,
    val vilage: String,
    val whatsappno: String
)


data class MwdMyProfileModel(
    val msg: String,
    val success: Boolean
)


data class MonthViewProfileModel(
    val detail: List<DetailMonthViewProfile>
)

data class DetailMonthViewProfile(
    val count: Int,
    val id: String,
    val month: String
)


data class WeeklyViewProfile(
    val detail: List<DetailDailyViewProfile>
)

data class DetailDailyViewProfile(
    val count: Int,
    val day: String,
    val id: String
)

data class DailyViewProfileModel(
    val detail: List<DetailDViewProfile>
)

data class DetailDViewProfile(
    val count: Int,
    val id: String
)


data class ComplainBoxSubModel(
    val id: String,
    val msg: String,
    val success: Boolean,
)


data class ComplainBoxDisplayModel(
    val cat: List<CatCBDisplay>
)

data class CatCBDisplay(
    val cat_name: String,
    val city: String,
    val complain: String,
    val distric: String,
    val fname: String,
    val moblino: String,
    val tehsil: String,
    val vilage: String,
    val whatsappno: String,
    val autoid: String,
    val tableid: String,
    val status: String,
    val form_status: String,
    val c_status: String,
    val loginname: String,
    val add_date: String,
    val complain_number: String,
    val next_date: String,
    val maker: String,
    val model_name: String,
    val manufacture_year: String,
    val registration_no: String,
    val mecanic_name: String

)


data class ComplainBoxCallModel(
    val msg: String,
    val success: Boolean,
    val task: List<Any>
)

data class CompalainFormModel(
    val msg: String,
    val success: Boolean
)


data class DocumentBoxAddModel(
    val msg: String,
    val success: Boolean
)


data class DocumentBoxDisplayModel(
    val cat: List<CatDocBoxDisplay>
)

data class CatDocBoxDisplay(
    val autoid: String,
    val cat_name: String,
    val city: String,
    val d_desc: String,
    val distric: String,
    val document: String,
    val fname: String,
    val moblino: String,
    val tableid: String,
    val tehsil: String,
    val vilage: String,
    val whatsappno: String,
    val next_date: String,
    val add_date: String,
    val status: String
)



data class DocBoxAddModel(
    val msg: String,
    val success: Boolean
)


data class EmpBorrowModel(
    val msg: String,
    val success: Boolean
)

data class SupBorrowModel(
    val msg: String,
    val success: Boolean
)



data class SupBorrowedListModel(
    val data: List<DataSupBorrowList>
)

data class DataSupBorrowList(
    val id: String,
    val leftamount: Double,
    val mobile: String,
    val name: String
)




data class DeliveryReportModel(
    val `data`: List<DataDeliveryReport>
)

data class DataDeliveryReport(
    val autoid: String,
    val cat_name: String,
    val city: String,
    val desc: String,
    val distric: String,
    val employee_name: String,
    val fname: String,
    val id: String,
    val moblino: String,
    val state: String,
    val tehsil: String,
    val vilage: String,
    val whatsappno: String,
    val fisrt_ser: String
)


data class AddViewDeliveryReportSubmitModel(
    val msg: String,
    val success: Boolean
)


/*data class DeliveryGenKListSubmitModel(
    val msg: String,
    val success: Boolean
)*/

data class DeliveryGenKListSubmitModel(
    val message: String
)


/*

data class DelievryReportViewGenListModel(
    val `data`: List<DataDRGenListView>
)

data class DataDRGenListView(
    val autoid: String,
    val cat_name: String,
    val check_accessories: String,
    val check_document: String,
    val check_oil_level: String,
    val city: String,
    val col_wh_no: String,
    val desc: String,
    val distric: String,
    val employee_name: String,
    val fisrt_services: String,
    val fname: String,
    val id: String,
    val installation: String,
    val key_delivery: String,
    val like_fb_insta_page: String,
    val moblino: String,
    val name_plat: String,
    val save_mobile_no: String,
    val second_services: String,
    val state: String,
    val sub_youtube_ch: String,
    val tehsil: String,
    val third_services: String,
    val toolkit_delivery: String,
    val tractor_number_plat: String,
    val vilage: String,
    val warranty_pollicy_discuusion: String,
    val whatsappno: String,
    val sub_flag: String
)*/

data class DelievryReportViewGenListModel(
    val `data`: List<DataDRGenListView>,
    val error: Boolean,
    val message: String
)

data class DataDRGenListView(
        val cat_name: String,
    val chasis_no: String,
    val chasis_no_input: String,
    val chasis_no_input_check: String,
    val chasis_print: String,
    val chasis_print_img: String,
    val chasis_print_img_check: String,
    val check_accessories: String,
    val check_accessories_img: String,
    val check_accessories_img_check: String,
    val check_document: String,
    val check_document_img: String,
    val check_document_img_check: String,
    val check_oil_level: String,
    val check_oil_level_img: String,
    val check_oil_level_img_check: String,
    val city: String,
    val col_wh_no: String,
    val col_wh_no_img_check: String,
    val cu_date: String,
    val cu_id: String,
    val delivey_photo: String,
    val delivey_photo_img: String,
    val delivey_photo_img_check: String,
    val desc: String,
    val distric: String,
    val employee_name: String,
    val engine_no: String,
    val engine_no_input: String,
    val engine_no_input_check: String,
    val filter: String,
    val fisrt_services: String,
    val fisrt_services_img: String,
    val fisrt_services_img_check: String,
    val fname: String,
    val gardan: String,
    val id: String,
    val installation: String,
    val installation_img: String,
    val installation_img_check: String,
    val key_delivery: String,
    val key_delivery_img: String,
    val key_delivery_img_check: String,
    val land_recored: String,
    val like_fb_insta_page: String,
    val like_fb_insta_page_img: String,
    val like_fb_insta_page_img_check: String,
    val loginid: String,
    val moblino: String,
    val model_name: String,
    val model_year: String,
    val name_plat: String,
    val name_plat_img: String,
    val name_plat_img_check: String,
    val rent: String,
    val rotavator: String,
    val save_mobile_no: String,
    val save_mobile_no_img: String,
    val save_mobile_no_img_check: String,
    val second_services: String,
    val second_services_img: String,
    val second_services_img_check: String,
    val speed_dreel: String,
    val state: String,
    val sub_flag: String,
    val sub_youtube_ch: String,
    val sub_youtube_ch_img: String,
    val sub_youtube_ch_img_check: String,
    val tehsil: String,
    val third_services: String,
    val third_services_img: String,
    val third_services_img_check: String,
    val toolkit_delivery: String,
    val toolkit_delivery_img: String,
    val toolkit_delivery_img_check: String,
    val tractor_number_plat: String,
    val tractor_number_plat_img: String,
    val tractor_number_plat_img_check: String,
    val tractor_type: String,
    val vilage: String,
    val warranty_pollicy_discuusion: String,
    val warranty_pollicy_discuusion_img: String,
    val warranty_pollicy_discuusion_img_check: String,
    val whatsapp_no: String,
    val whatsappno: String,
    val smart_card: String,
    val smart_card_date: String
)


data class FirstServiceGLModel(
    val msg: String,
    val success: Boolean
)


data class FirstServiceModel(
    val `data`: List<DataFirstService>
)

data class DataFirstService(
    val autoid: String,
    val cat_name: String,
    val city: String,
    val desc: String,
    val distric: String,
    val employee_name: String,
    val fname: String,
    val id: String,
    val moblino: String,
    val state: String,
    val tehsil: String,
    val vilage: String,
    val whatsappno: String,
    val follow_date: String
)

data class FirstServiceAddModel(
    val msg: String,
    val success: Boolean
)

data class FirstServiceAddTwoModel(
    val msg: String,
    val success: Boolean
)


data class DisplayReportCountModel(
    val cat: List<CatDelieryReport>
)

data class CatDelieryReport(
    val first_ser: String,
    val genral_list: String,
    val second_ser: String,
    val third_ser: String
)

data class ReviewHistoryCount (

        @SerializedName("first_meeting" )      var        firstMeeting :       String? =     null,
        @SerializedName("delivry"       )      var        delivry      :       String? =     null,
        @SerializedName("drop_inq"      )      var        dropInq      :       String? =     null,
        @SerializedName("hot"           )      var        hot          :       String? =     null,
        @SerializedName("warm"          )      var        warm         :       String? =     null,
        @SerializedName("cold"          )      var        cold         :       String? =     null,
        @SerializedName("selllost"      )      var        selllost     :       String? =     null,
        @SerializedName("service and complaint")            var     service_complaint : String? = null,
        @SerializedName("walking visit" )          var          walking_visit   :     String?   = null,
        @SerializedName("number plate fitting")            var     number_plate_fitting   : String? = null,
        @SerializedName("new_visit")            var     new_visit   : String? = null,
        @SerializedName("activities"    )      var        activities   :       String? =     null

)

data class SecondServiceAddTwo(
        val msg: String,
        val success: Boolean
)

data class ThirdSmartCardVendorModel(
    val `data`: List<DataSmartCardVendor>
)

data class DataSmartCardVendor(
    val id: String,
    val mobile: String,
    val name: String
)


data class FirstAddSmartCardModel(
    val cuid: String,
    val id: Int,
    val msg: String,
    val success: Boolean
)


data class AddPointValueSecondModel(
    val cat: List<CatAddPointValueSecond>
)

data class CatAddPointValueSecond(
    val auto_id: String,
    val cat_id: String,
    val cat_name: String,
    val city: String,
    val desc: String,
    val distric: String,
    val eid: String,
    val employee_name: String,
    val fname: String,
    val lname: String,
    val moblino: String,
    val state: String,
    val tehsil: String,
    val vilage: String,
    val whatsappno: String,
    val cuname: String
)


data class AddSmartCardSecondSubmitModel(
    val msg: String,
    val success: Boolean
)


data class AddSmartCardThirdModel(
    val msg: String,
    val success: Boolean
)


data class AddSmartCardVerfiedOtpModel(
    val msg: String,
    val success: Boolean
)



data class VendorLoginModel(
    val `data`: List<DataVendorLogin>,
    val msg: String,
    val success: Boolean
)

data class DataVendorLogin(
    val emp_id: String,
    val emp_name: String,
    val module_id: String,
    val module_name: String
)


data class LoginModelSSP(
    val `data`: List<DataSSPLogin>,
    val msg: String,
    val success: Boolean
)

data class DataSSPLogin(
    val emp_id: String,
    val emp_name: String,
    val module_id: String,
    val module_name: String
)


data class ViewDocVendoreModel(
    val cat: List<CatViewDocVen>
)

data class CatViewDocVen(
    val cu_name: String,
    val dealprice: String,
    val sales_date: String,
    val village: String
)


/*
data class SSP_ViewAccountDataBankModel(
    val cat: List<CatSSPViewAcc>
)

data class CatSSPViewAcc(
    val cu_name: String,
    val insentive: String,
    val point_value: String,
    val product_name: String,
    val sales_date: String,
    val smart_card: String,
    val village: String
)*/


data class SSP_ViewAccountDataBankModel(
    val cat: List<CatSSPViewAcc>
)

data class CatSSPViewAcc(
    val cu_name: String,
    val insentive: String,
    val point_value: String,
    val product_name: String,
    val sales_date: String,
    val smart_card: String,
    val status: String,
    val village: String,
    val w_amt: String,
    val w_date: String,
    val bal: String
)

data class RequestProductListModel(
    val `data`: List<DataRequestProductAdd>
)

data class DataRequestProductAdd(
    val id: String,
    val state: String
)



data class VendorAddRequestSSPMode(
    val city: List<CityVendorAdd>
)

data class CityVendorAdd(
    val id: String,
    val vendor_id: String,
    val vname: String
)

data class AddRequestSubmitSSPModel(
    val msg: String,
    val success: Boolean
)

data class ReqGaneralListSSPModel(
    val cat: List<CatReqGaneralSSP>
)

data class CatReqGaneralSSP(
    val customer_name: String,
    val mobile: String,
    val id: String,
    val product_name: String,
    val status: String,
    val vendor: String,
    val add_date: String,
    val count: String,
    val village: String
)



data class VendorReqGaneralModel(
    val cat: List<CatVendorReqGaneral>
)

data class CatVendorReqGaneral(
    val customer_name: String,
    val id: String,
    val mobile: String,
    val product_name: String,
    val status: String,
    val vendor: String,
    val add_date: String,
    val village: String
)

data class cancel_buttion_model(
    val msg: String,
    val success: Boolean
)

data class Accept_butiion_model(
    val msg: String,
    val success: Boolean
)

data class ssp_delivery_buttion_model(
    val msg: String,
    val success: Boolean
)

data class vendor_delivery_button_model(
    val cuid: Any,
    val id: Int,
    val msg: String,
    val success: Boolean
)

data class ssp_databank_employ_model(
    val `data`: List<Dataak>
)

data class Dataak(
    val ename: String,
    val id: String
)

data class checkornot_model_ak(
    val msg: String,
    val success: Boolean
)

data class send_location_model_ak(
    val msg: String,
    val success: Boolean
)

data class emp_tracking_mode(
    val `data`: List<Datatime>
)

data class Datatime(
    val latitude: String,
    val longitude: String,
    val time: String
)

data class emp_startloc_endloc_model(
    val `data`: List<Datastartloc>
)

data class Datastartloc(
    val date: String,
    val location: String,
    val time: String
)

data class All_data_show_tracking_model(
    val `data`: List<Dataalldata>
)

data class Dataalldata(
    val date: String,
    val location: String,
    val time: String
)

data class filter_emp_model(
    val `data`: List<Datafilter>
)

data class Datafilter(
    val date: String,
    val location: String,
    val time: String
)


data class ShowTripModel(
    val `data`: List<Datatripmodel>
)

data class Datatripmodel(
    val date: String,
    val location: String,
    val time: String
)

data class line_start_to_end_model(
    val `data`: List<Datastart_to_en>
)

data class Datastart_to_en(
    val date: String,
    val latitude: String,
    val location: String,
    val longitude: String,
    val time: String
)

data class ssp_zeroday_id_send_model(
    val msg: String,
    val success: Boolean
)

data class emp_filter_date_traking_model(
    val `data`: List<Datafilter1>
)

data class Datafilter1(
    val date: String,
    val latitude: String,
    val location: String,
    val longitude: String,
    val time: String
)


data class meeting_emp_model(
    val `data`: List<Datameeting>
)

data class Datameeting(
    val link: String
)

data class cashbook_inButton_model(
    val msg: String,
    val success: Boolean
)

data class cashbook_getdata_model(
        val banktotal: String,
        val cashtotal: String,
        val cuintotal: Int,
        val cuouttotal: Int,
        val `data`: List<Dataentry>,
        val fulltotal: String,
        val today_transfer: String

        )

data class Dataentry(
//    val c_date: String,
//    val c_desc: String,
//    val id: String,
//    val img: String,
//    val cuintotal: String,
//    val cuouttotal: String,
//    val inamount: String,
//    val outamount: String,
//    val time: String,
//    val tran_type: String,
//    val type: String

    val c_date: String,
    val c_desc: String,
    val cuintotal: String,
    val cuouttotal: String,
    val leger_type: String,
    val leger_name: String,
    val m_type: String,
    val id: String,
    val img: String,
    val inamount: String,
    val outamount: String,
    val time: String,
    val tran_type: String,
    val type: String,
    val today_transfer: String
)


data class ladger_getdata_model(
        val banktotal: String,
        val cashtotal: String,
        val fulltotal: String,
        val cuintotal: Int,
        val cuouttotal: Int,
        val `data`: List<Dataentry1>,
        val today_transfer: String

)

data class Dataentry1(
        val tableid: String,
        val add_date: String,
        val product: String,
        val CustomerName: String,
        val model: String,
        val Description: String,
        val credit: String,
        val debit: String
)

class ItemDateComparator1 : Comparator<Dataentry1> {
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

        override fun compare(o1: Dataentry1?, o2: Dataentry1?): Int {
                return try {
                        val date1 = dateFormat.parse(o1?.add_date )
                        val date2 = dateFormat.parse(o2?.add_date)
                        date1.compareTo(date2)
                } catch (e: Exception) {
                        0
                }
        }
}

data class cashbook_getDate_model(
    val cat: List<Catgetdate1>
)

data class Catgetdate1(
    val cudate: String
)

data class transfer_cashtobank_model(
    val msg: String,
    val success: Boolean
)

data class cashbook_history_model(
    val `data`: List<Datahistory>
)

data class Datahistory(
    val amount: String,
    val c_date: String,
    val c_desc: String,
    val c_from: String,
    val c_to: String,
    val id: String,
    val time: String
)

data class cashbook_dateFilter_model(
    val `data`: List<Datafilter123>
)

data class Datafilter123(
    val c_date: String,
    val c_desc: String,
    val cuintotal: String,
    val cuouttotal: String,
    val leger_type: String,
    val leger_name: String,
    val m_type: String,
    val id: String,
    val img: String,
    val inamount: String,
    val outamount: String,
    val time: String,
    val tran_type: String,
    val type: String,
    val today_transfer: String
)

data class emp_modelname_model(
    val `data`: List<DataGetmodel>
)

data class DataGetmodel(
    val id: String,
    val model_name: String
)

data class new_visit_model(
    val msg: String,
    val success: Boolean
)

data class addimage_attachfile_model(
    val msg: String,
    val success: Boolean
)

data class model_msg(
        val `data`: List<DataMsg>
)

data class DataMsg(
        val id: String,
        val deal_stage: String,
        val video_link1: String,
        val video_link2: String,
        val video_link3: String,
        val video_link4: String,
        val video_link5: String,
        val detail: String,
        val doc_file: String
)

data class show_image_model(
    val `data`: List<Datashowimage>
)

data class Datashowimage(
    val Image: String,
    val cdate: String
)

data class Deal_model(
    val msg: String,
    val success: Boolean
)

data class Deal_notattend_model(
    val cat: List<Catnotattend>
)

data class Catnotattend(
    val autoid: String,
    val booking: String,
    val booking_amt: String,
    val cat_id: String,
    val cat_name: String,
    val city: String,
    val delivry: String,
    val desc: String,
    val distric: String,
    val employee_name: String,
    val fname: String,
    val name: String,
    val lname: String,
    val id: String,
    val inq_type: String,
    val moblino: String,
    val other_mobile: String,
    val login_emp: String,
    val model_name: String,
    val reason: String,
    val sell_lost: String,
    val tehsil: String,
    val tractorname: String,
    val vdate: String,
    val follow_up_type: String,
    val sor_of_inq: String,
    val model: String,
    val added: String,
    val vemp: String,
    val vilage: String,
    val whatsappno: String,
    val file_count: String,
    val add_date: String,
    val passing_type: String,
    val payment_type: String,
    val deal_type: String,
    val loan_type: String,
    val deal_stage: Any,
    val current_stage_name: Any,
    val inq_new: String,
    val inq_overdue: String,
    val type_inq: String,
    val phn_flag: String,
    val whatsapp_flag: String,
    val sms_flag: String,
    val phn_review: String,
    val sms_review: String,
    val whatsapp_review: String,
    val skip_flag: String,
    val submit_done: String,
    val customer_price: String,
    val market_value: String,
    val diffrance: String,
    val regi_noold: String,
    val makerold: String,
    val modelold: String,
    val model_yold: String,
    val registration_no: String,
    val model_maker: String,
    val not_attend_time: String,
    val first_time_attend: String,
    val inq_date: String,
    val manufacture_year: String

    )


class ItemDateComparator : Comparator<Catnotattend> {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    override fun compare(item1: Catnotattend, item2: Catnotattend): Int {
        return try {
            val date1 = dateFormat.parse(item1.vdate)
            val date2 = dateFormat.parse(item2.vdate)
            date1.compareTo(date2)
        } catch (e: Exception) {
            0
        }
    }
}


data class deal_hotInquire_model(
    val cat: List<CathotInquire>
)

data class CathotInquire(
    val autoid: String,
    val booking: String,
    val booking_amt: String,
    val cat_id: String,
    val cat_name: String,
    val city: String,
    val delivry: String,
    val desc: String,
    val distric: String,
    val employee_name: String,
    val fname: String,
    val id: String,
    val inq_type: String,
    val moblino: String,
    val model_name: String,
    val reason: String,
    val sell_lost: String,
    val tehsil: String,
    val tractorname: String,
    val vdate: String,
    val vemp: String,
    val vilage: String,
    val whatsappno: String
)

data class deal_stage_call_sms_what_model(
    val msg: String,
    val success: Boolean
)

data class deal_Remainder_model(
    val id: Int,
    val msg: String,
    val success: Boolean
)

data class deal_otpVerify_model(
    val msg: String,
    val success: Boolean
)

data class deal_firstmeeting_deal_model(
    val id: Int,
    val msg: String,
    val success: Boolean
)

data class deal_setCount_model(
    val booking: String,
    val cold: String,
    val delivry: String,
    val drop_inq: String,
    val first_meeting: String,
    val hot: String,
    val make_an_Offer: String,
    val next_activity: String,
    val not_attand: String,
    val overdue: String,
    val second_metting: String,
    val selllost: String,
    val warm: String
)

data class deal_set_count_model(
    val booking: String,
    val cold: String,
    val delivry: String,
    val drop_inq: String,
    val first_meeting: String,
    val hot: String,
    val make_an_Offer: String,
    val next_activity: String,
    val not_attand: String,
    val negotiation: String,
    val follow_up: String,
    val overdue: String,
    val second_metting: String,
    val selllost: String,
    val follow_first: String,
    val follow_third: String,
    val Deller_meeting: String,
    val warm: String
)

data class deal_stage_history_model(
    val Add_Employee_date: String,
    val Add_Employee_stage: String,
    val Add_Employee_status: String,

    val First_metting_date: String,
    val First_metting_stage: String,
    val First_metting_status: String,

    val Follow_person_date: String,
    val Follow_person_stage: String,
    val Follow_person_status: String,

    val Not_attand_date: String,
    val Not_attand_stage: String,
    val Not_attand_status: String,

    val add_date_date: String,
    val add_date_stage: String,
    val add_date_status: String,

    val booking_date: String,
    val booking_stage: String,
    val booking_status: String,

    val delivery_date: String,
    val delivery_stage: String,
    val delivery_status: String,

    val second_metting_date: String,
    val second_metting_stage: String,
    val second_metting_status: String,


    val Next_Activity_Plan1_stage: String,
    val Next_Activity_Plan1_date: String,
    val Next_Activity_Plan1_status: String,


    val Next_Activity_Plan2_stage: String,
    val Next_Activity_Plan2_date: String,
    val Next_Activity_Plan2_status: String,

    val Next_Activity_Plan3_stage: String,
    val Next_Activity_Plan3_date: String,
    val Next_Activity_Plan3_status: String,

    val make_an_offer1_stage: String,
    val make_an_offer1_date: String,
    val make_an_offer1_status: String,

    val make_an_offer2_stage: String,
    val make_an_offer2_date: String,
    val make_an_offer2_status: String,


    val sellost_date: String,
    val sellost_stage: String,
    val sellost_status: String,

    val drop_stage: String,
    val drop_date: String,
    val drop_status: String,

    val recent_note: String,
    val location: String
)

data class deal_threestage_model(
        val msg: String,
        val success: Boolean
)

data class deal_negoatiation_web(
        val msg: String,
        val success: Boolean
)

data class delivery_screen_model(
    val Add_inq_price: String,
    val Add_inq_stage: String,
    val Basic_price: String,
    val Basic_stage: String,
    val booking_price: String,
    val booking_stage: String,
    val dp_price: String,
    val dp_stage: String,
    val first_meet_price: String,
    val first_meet_stage: String,
    val loan_stage: String,
    val loan_price: String,
    val next_act_price: String,
    val next_act_stage: String,
    val offer_meet_price: String,
    val offer_meet_stage: String,
    val old_rc_price: String,
    val old_rc_stage: String,
    val total: Int
)

data class DescriptionData(
        val `data`: List<ReviewCat1>
)


data class ReviewCat1 (
        @SerializedName("location"    ) var location   : String? = null,
        @SerializedName("emp_id"      ) var empId      : String? = null,
        @SerializedName("recent_note" ) var recentNote : String? = null,
        @SerializedName("follow_up"   ) var followUp   : String? = null,
        @SerializedName("date"        ) var date       : String? = null

)


data class getModelData(
    val `data`: List<Datamodel>
)

data class Datamodel(
    val id: String,
    val model_name: String
)

data class chckeout_model(
    val msg: String,
    val success: Boolean
)

data class perfomance_model(
    val cold_achiv: String,
    val cold_note: String,
    val cold_target: String,
    val dataentry_achiv: String,
    val dataentry_note: String,
    val dataentry_target: String,
    val delivery_achiv: String,
    val delivery_note: String,
    val delivery_target: String,
    val drop_achiv: String,
    val drop_note: String,
    val drop_target: String,
    val hot_achiv: String,
    val hot_note: String,
    val hot_target: String,
    val new_inq_achiv: String,
    val new_inq_note: String,
    val new_inq_target: String,
    val selllost_achiv: String,
    val selllost_note: String,
    val selllost_target: String,
    val warm_achiv: String,
    val warm_note: String,
    val warm_target: String,
    val new_visit_target: String,
    val new_visit_achiv: String,
    val new_note: String,
    val activity_visit_target: String,
    val activity_visit_achiv: String,
    val activity_note: String,
    val follow_up_target: String,
    val follow_up_achiv: String,
    val follow_up_note: String,
    val overdue_target: String,
    val overdue_achiv: String,
    val overdue_note: String,
    val not_attand_target: String,
    val not_attand_achiv: String,
    val not_attand_note: String,
    val first_metting_target: String,
    val first_metting_achiv: String,
    val first_metting_note: String,
    val delivery_target_market: String,
    val delivery_achiv_market: String,
    val delivery_note_market: String,
    val selllost_target_market: String,
    val selllost_achiv_market: String,
    val selllost_note_market: String,
    val drop_target_market: String,
    val drop_achiv_market: String,
    val drop_note_market: String,
    val databank_target_market: String,
    val databank_achiv_market: String,
    val databank_note_market: String,
    val newcomplain_target_market: String,
    val newcomplain_achiv_market: String,
    val newcomplain_note_market: String,
    val profiie_target_market: String,
    val profile_achiv_market: String,
    val profile_note_market: String,
    val assign_inq: String
)
data class FeedBackCall(
    val cat: List<Cat1>
)

data class Cat1(
        val add_date: String,
        val added: String,
        val autoid: String,
        val booking: Any,
        val booking_amt: Any,
        val cat_id: String,
        val cat_name: String,
        val city: String,
        val deal_stage: Any,
        val current_stage_name: Any,
        val delivry: Any,
        val desc: Any,
        val distric: String,
        val employee_name: String,
        val file_count: String,
        val fname: String,
        val follow_up_type: Any,
        val id: String,
        val inq_type: Any,
        val login_emp: String,
        val moblino: String,
        val model: Any,
        val model_name: Any,
        val reason: Any,
        val sell_lost: Any,
        val sor_of_inq: Any,
        val tehsil: String,
        val tractorname: Any,
        val vdate: Any,
        val vemp: String,
        val vilage: String,
        val whatsappno: String,
        val feedback_call_done: String
)
data class FeedBackCall_call(
    val msg: String,
    val success: Boolean
)
data class FeedbackCall_add(
    val msg: String,
    val success: Boolean
)

data class CommonSearch(
        val cat: List<commonSearch>
) {
        data class commonSearch(
                val added: String,
                val autoid: String,
                val booking: Any,
                val booking_amt: Any,
                val cat_id: String,
                val cat_name: String,
                val city: String,
                val deal_stage: Any,
                val current_stage_name: Any,
                val delivry: Any,
                val desc: Any,
                val distric: String,
                val employee_name: String,
                val payment_type: String,
                val passing_type: String,
                val file_count: Any,
                val fname: String,
                val name: String,
                val lname: String,
                val other_mobile: String,
                val follow_up_type: Any,
                val id: String,
                val inq_new: String,
                val inq_type: String,
                val inq_overdue: String,
                val login_emp: String,
                val moblino: String,
                val model: Any,
                val model_name: Any,
                val reason: Any,
                val sell_lost: Any,
                val sor_of_inq: Any,
                val tehsil: String,
                val tractorname: Any,
                val type_inq: String,
                val vdate: Any,
                val vemp: String,
                val vilage: String,
                val whatsappno: String,
                val loan_type: String

        )
}

   /* {
            data class commonSearch(
                    val added: String,
                    val autoid: String,
                    val booking: String,
                    val booking_amt: String,
                    val cat_id: String,
                    val cat_name: String,
                    val city: String,
                    val deal_stage: String,
                    val delivry: String,
                    val desc: String,
                    val distric: String,
                    val employee_name: String,
                    val file_count: String,
                    val fname: String,
                    val follow_up_type: String,
                    val id: String,
                    val inq_new: String,
                    val inq_overdue: String,
                    val login_emp: String,
                    val moblino: String,
                    val model: String,
                    val model_name: String,
                    val reason: String,
                    val sell_lost: String,
                    val sor_of_inq: String,
                    val tehsil: String,
                    val tractorname: String,
                    val type_inq: String,
                    val vdate: String,
                    val vemp: String,
                    val vilage: String,
                    val whatsappno: String,
                    val passing_type: String,
                    val payment_type: String,
                    val loan_type: String

            )
    }*/
data class VillageList(
    val name: List<Name>
) {
    data class Name(
        val count: String,
        val vid: String,
        val vname: String
    )
}
data class VillageListShow(
    val cat: List<Cat>,
) {
    data class Cat(
            val add_date: String,
            val added: String,
            val autoid: String,
            val booking: String,
            val booking_amt: String,
            val cat_id: String,
            val cat_name: String,
            val city: String,
            val deal_stage: String,
            val current_stage_name: Any,
            val delivry: String,
            val desc: String,
            val distric: String,
            val employee_name: String,
            val feedback_call_done: String,
            val file_count: String,
            val fname: String,
            val follow_up_type: String,
            val id: String,
            val inq_type: String,
            val login_emp: String,
            val moblino: String,
            val model: String,
            val model_name: String,
            val reason: String,
            val sell_lost: String,
            val sor_of_inq: String,
            val tehsil: String,
            val tractorname: String,
            val vdate: String,
            val vemp: String,
            val vilage: String,
            val whatsappno: String
    )
}
data class Catlist(
    val cat: List<Cat>
) {
    data class Cat(
        val cat_id: String,
        val cat_list: String,
        val count: String
    )
}
data class VillageListProfile(
    val name: List<Name>
) {
    data class Name(
        val count: String,
        val vid: String,
        val vname: String
    )
}
data class VillageListShowProfile(
    val cat: List<Cat>
) {
    data class Cat(
        val autoid: String,
        val cat_id: String,
        val cat_name: String,
        val city: String,
        val distric: String,
        val employee_name: String,
        val fname: String,
        val login_emp: String,
        val moblino: String,
        val tehsil: String,
        val vilage: String,
        val whatsappno: String
    )
}

data class ScoreBoardView(
    val cat: List<Cat>
) {
    data class Cat(
            //DataBank
            val cat_name: String,
            val category: String,
            val cuu_date: String,
            val description: String,
            val district: String,
            val ename: String,
            val fname: String,
            val lname: String,
            val loginname: String,
            val make_name: String,
            val moblino: String,
            val model: String,
            val model_y: String,
            val sor_of_inq: String,
            val tehsil: String,
            val vilage: String,
            val whatsappno: String,
            val filter_type3: String,

            //InquiryBank
            val follow_up_type: String,
            val next_date: String,
            val type_inq: String,

            //services
            val cgst_val: String,
            val city: String,
            val cname: String,
            val comno: String,
            val deal_price: String,
            val distric: String,
            val entry_date: String,
            val macemp: String,
            val mobile: String,
            val p_type: String,
            val sgst_val: String,
            val state: String,
            val taxable_val: String,
            val type: String,
            val village: String,
            val work_ser: String,
            val dataadd: String,
            val cdate: String,
            val cat: String,


            //delivery
            val de_model_name: String,
            val delivery: String,

            //selllost
            val sell_lost: String,
            val sell_model: String,

            //dropInquiry
            val drop_inq: String,

            //Document collection
            val catname: String,
            val desc: String,
            val mno: String,
            val who: String,

            //New Complain
            //Add Profile
          //Edit Profile

            //Present
            val date: String
            //Travaling
            //followup
    )
}
data class Booking_Next(
    val msg: String,
    val success: Boolean
)
data class Example(
    val `data`: Int,
    val error: Boolean,
    val message: String
)
data class RcUpdate(
    val data: List<rcUpdate>,
    val error: Boolean,
    val message: String
){
    data class rcUpdate(
            val Agent_free: Any,
            val Policy_date: Any,
            val adhar_photo: String,
            val adhar_photo_check: String,
            val agent_fee: String,
            val agent_fee_check: String,
            val amount: String,
            val amount_check: String,
            val app_status: String,
//            val approve_status: String,
            val b_date: String,
            val b_date_check: String,
            val b_p_photo: String,
            val b_p_photo_check: String,
            val b_photo: String,
            val b_photo_check: String,
            val b_type: String,
            val b_type_check: String,
            val battery: String,
            val battery_check: String,
            val bno: String,
            val bno_check: String,
            val booking_amt: String,
            val booking_amt_check: String,
            val booking_sub: String,
            val bumper: String,
            val receive_fitment_flag: String,
            val level_fitment_approve: String,
            val bumper_check: String,
            val c_amount: String,
            val c_amount_check: String,
            val chasis_print: String,
            val chasis_print_check: String,
            val chasisno: String,
            val chasisno_check: String,
            val check1_photo: String,
            val check1_photo_check: String,
            val check_amt: String,
            val check_amt_check: String,
            val check_date: String,
            val check_date_check: String,
            val check_neft_rtgs: String,
            val check_neft_rtgs_check: String,
            val check_photo: String,
            val check_photo_check: String,
            val cibil_score: String,
            val cibil_score_check: String,
            val city: String,
            val city_check: String,
            val crtm: Any,
            val deal_price: String,
            val deal_price_check: String,
            val deal_price_in_word: String,
            val deal_price_in_word_check: String,
            val delivery_date: String,
            val delivery_date_check: String,
            val delivry_photo: String,
            val delivry_photo_check: String,
            val description: String,
            val description_check: String,
            val distric: String,
            val distric_check: String,
            val dmodelname: String,
            val dmodelname_check: String,
            val drowbar: String,
            val drowbar_check: String,
            val ec_photo: String,
            val ec_photo_check: String,
            val ecard_photo: String,
            val ecard_photo_check: String,
            val emp: String,
            val emp_check: String,
            val emp_id: String,
            val emp_id_check: String,
            val engineno: String,
            val engineno_check: String,
            val expriy_date: Any,
            val fi_date: String,
            val fi_date_check: String,
            val filter: Any,
            val finance_from: String,
            val finance_from_check: String,
            val fitment: Any,
            val fname: String,
            val fname_check: String,
            val gst: String,
            val gst_check: String,
            val hitch: String,
            val hitch_check: String,
            val hood: String,
            val hood_check: String,
            val id: String,
            val insurance: String,
            val insurance_check: String,
            val insurance_com_name: Any,
            val l_c_photo: String,
            val l_c_photo_check: String,
            val l_sec_amt: String,
            val l_sec_amt_check: String,
            val land_details: String,
            val land_details_check: String,
            val lb_pb_photo: String,
            val lb_pb_photo_check: String,
            val lloan_charge: String,
            val lloan_charge_check: String,
            val lname: Any,
            val lname_check: String,
            val loan_amount: String,
            val loan_amount_check: String,
            val loan_charge: String,
            val loan_charge_check: String,
            val m_year: String,
            val m_year_check: String,
            val make: String,
            val make_check: String,
            val mobileno: String,
            val mobileno_check: String,
            val model: String,
            val model_check: String,
            val model_name: String,
            val model_name_check: String,
            val neft_rtgs_amt: String,
            val neft_rtgs_amt_check: String,
            val neft_rtgs_date: String,
            val neft_rtgs_date_check: String,
            val neft_rtgs_photo: String,
            val neft_rtgs_photo_check: String,
            val noc: String,
            val noc_check: String,
            val noc_photo: String,
            val noc_photo_check: String,
            val num_plate_order: Any,
            val num_plate_recive: Any,
            val number_plat: String,
            val number_plat_check: String,
            val old_t_amount: String,
            val old_t_amount_check: String,
            val old_vehicle_photo: String,
            val old_vehicle_photo_check: String,
            val p_desc: String,
            val p_desc_check: String,
            val p_photo: String,
            val p_photo_check: String,
            val paper_expence: String,
            val paper_expence_check: String,
            val pinsurance: Any,
            val policy_amount: Any,
            val policy_number: Any,
            val product_name: String,
            val product_name_check: String,
            val r_e_name: String,
            val r_e_name_check: String,
            val rc_book_financial: Any,
            val rc_book_update_in: Any,
            val rc_update: Any,
            val recbook_photo: String,
            val recbook_photo_check: String,
            val recive_date: Any,
            val ref_name: String,
            val ref_name_check: String,
            val ref_no: String,
            val ref_no_check: String,
            val registration_num: Any,
            val rto: String,
            val rto_check: String,
            val rto_passing: String,
            val rto_passing_check: String,
            val rto_tax: String,
            val rto_tax_check: String,
            val sale_return: String,
            val sar_id_photo: String,
            val sar_id_photo_check: String,
            val sectiondate: String,
            val sectiondate_check: String,
            val sign_veri: String,
            val sign_veri_check: String,
            val sname: String,
            val sname_check: String,
            val stage: String,
            val stage_check: String,
            val state: String,
            val state_check: String,
            val tax: Any,
            val tax_amount: Any,
            val tehsill: String,
            val tehsill_check: String,
            val toolkit: String,
            val toolkit_check: String,
            val toplink: String,
            val toplink_check: String,
            val tyre: String,
            val tyre_check: String,
            val variants: String,
            val variants_check: String,
            val village: String,
            val village_check: String,
            val whno: String,
            val whno_check: String,
            val skim: String,
            val atype: String,
            val skim_check: String,
            val atype_check: String,
            val adhar_back: String,
            val adhar_back_check: String,
            val election_back: String,
            val election_back_check: String,
            val p_photo_back: String,
            val p_photo_back_check: String,
            val rcbook_back: String,
            val rcbook_back_check: String,
            val elec_back: String,
            val elec_back_check: String,
            val b_pass_back: String,
            val b_pass_back_check: String,

            val cash_date: String,
            val cash_date_check: String,
            val cash_amount: String,
            val cash_amount_check: String,
            val cash_description: String,
            val cash_description_check: String,

            //Passing Details
            val register_no: String,
            val cskim: String
    )
}

data class RcbookUpdate(
    val msg: String,
    val success: Boolean
)
data class VillageList1(
    val detail: List<Detail>
) {
    data class Detail(
            val type: String,
            val village: String,
            val count: String
    )
}
data class ShowVilageDetail(
    val detail: List<Detail>,
    val error: Boolean,
    val message: String
) {
    data class Detail(
        val elec_back_check: String,
        val election_back: String,
        val election_back_check: String,
        val emp: String,
        val emp_check: String,
        val emp_id: String,
        val emp_id_check: String,
        val empid: String,
        val engineno: Any,
        val engineno_check: String,
        val expriy_date: Any,
        val expriy_date_check: String,
        val fi_date: Any,
        val fi_date_check: String,
        val filter: Any,
        val final_amt: String,
        val finance_from: Any,
        val finance_from_check: String,
        val finish: Any,
        val fitment: Any,
        val fitment_img: String,
        val fitment_img_check: String,
        val fname: String,
        val fname_check: String,
        val gst: Any,
        val gst_check: String,
        val hitch: Any,
        val hitch_check: String,
        val hood: Any,
        val hood_check: String,
        val id: String,
        val ins_bank_type: Any,
        val ins_bank_type_check: String,
        val ins_cash_amt: Any,
        val ins_cash_amt_check: String,
        val ins_cash_date: Any,
        val ins_cash_date_check: String,
        val ins_cash_desc: Any,
        val ins_cash_desc_check: String,
        val ins_cheque_amt: Any,
        val ins_cheque_amt_check: String,
        val ins_cheque_date: Any,
        val ins_cheque_date_check: String,
        val ins_cheque_desc: Any,
        val ins_cheque_desc_check: String,
        val ins_neft_amt: Any,
        val ins_neft_amt_check: String,
        val ins_neft_date: Any,
        val ins_neft_date_check: String,
        val ins_neft_desc: Any,
        val ins_neft_desc_check: String,
        val ins_pay_type: Any,
        val ins_pay_type_check: String,
        val insurance: Any,
        val insurance_check: String,
        val insurance_com_name: Any,
        val insurance_com_name_check: String,
        val key_otp: Any,
        val l_c_photo: Any,
        val l_c_photo_check: String,
        val l_sec_amt: Any,
        val l_sec_amt_check: String,
        val land_details: Any,
        val land_details_check: String,
        val lb_pb_photo: Any,
        val lb_pb_photo_check: String,
        val leftamount: String,
        val lloan_charge: Any,
        val lloan_charge_check: String,
        val lname: Any,
        val lname_check: String,
        val loan_amount: Any,
        val loan_amount_check: String,
        val loan_charge: Any,
        val loan_charge_check: String,
        val loan_next_click: String,
        val loan_section: String,
        val loans: String,
        val m_year: Any,
        val m_year_check: String,
        val make: Any,
        val make_check: String,
        val mobileno: String,
        val mobileno_check: String,
        val model: Any,
        val model_check: String,
        val model_name: Any,
        val model_name_check: String,
        val n_date: Any,
        val neft_rtgs_amt: Any,
        val neft_rtgs_amt_check: String,
        val neft_rtgs_amt_word: Any,
        val neft_rtgs_date: Any,
        val neft_rtgs_date_check: String,
        val neft_rtgs_photo: Any,
        val neft_rtgs_photo_check: String,
        val noc: Any,
        val noc_check: String,
        val noc_photo: Any,
        val noc_photo_check: String,
        val num_plate_order: Any,
        val num_plate_order_check: String,
        val num_plate_recive: Any,
        val number_plat: Any,
        val number_plat_check: String,
        val old_t_amount: Any,
        val old_t_amount_check: String,
        val old_t_amt_word: Any,
        val old_vehicle_photo: Any,
        val old_vehicle_photo_check: String,
        val other_amt: String,
        val other_app: String,
        val other_date: String,
        val other_desc: String,
        val p_desc: Any,
        val p_desc_check: String,
        val p_photo: String,
        val p_photo_back: String,
        val p_photo_back_check: String,
        val p_photo_check: String,
        val paper_expence: Any,
        val paper_expence_check: String,
        val pinsurance: Any,
        val pinsurance_check: String,
        val policy_amount: Any,
        val policy_amount_check: String,
        val policy_date_check: String,
        val policy_number: Any,
        val policy_number_check: String,
        val product_name: String,
        val product_name_check: String,
        val r_e_name: Any,
        val r_e_name_check: String,
        val rc_book_financial: Any,
        val rc_book_update_in: Any,
        val rc_book_updatein_check: String,
        val rc_cu_img_one_check: String,
        val rc_cu_img_two_check: String,
        val rc_customer_check: String,
        val rc_fitment_check: String,
        val rc_num_plat_check: String,
        val rc_num_plat_recive_check: String,
        val rc_recive_date_check: String,
        val rc_reg_number_check: String,
        val rc_update: Any,
        val rc_update_check: String,
        val rc_update_customer: String,
        val rcbook_back: Any,
        val rcbook_back_check: String,
        val rcbook_financer_check: String,
        val recbook_photo: Any,
        val recbook_photo_check: String,
        val recive_date: Any,
        val ref_name: String,
        val ref_name_check: String,
        val ref_no: String,
        val ref_no_check: String,
        val refal_amt: Any,
        val refal_amt_check: String,
        val refal_desc: Any,
        val refal_desc_check: String,
        val refal_mobile: Any,
        val refal_mobile_check: String,
        val refal_name: Any,
        val refal_name_check: String,
        val refal_val: Any,
        val refal_val_check: String,
        val reg_no: Any,
        val reg_no_check: String,
        val register_no: Any,
        val register_no_check: String,
        val registration_num: Any,
        val rto: Any,
        val rto_check: String,
        val rto_new: Any,
        val rto_passing: Any,
        val rto_passing_check: String,
        val rto_tax: Any,
        val rto_tax_check: String,
        val sale_return: String,
        val sar_id_photo: Any,
        val sar_id_photo_check: String,
        val sectiondate: Any,
        val sectiondate_check: String,
        val sent_sms: String,
        val sign_veri: Any,
        val sign_veri_check: String,
        val skim: Any,
        val skim_amt: Any,
        val skim_check: String,
        val sms_s_date: Any,
        val sname: String,
        val sname_check: String,
        val stage: Any,
        val stage_check: String,
        val state: String,
        val state_check: String,
        val t_bank_type: Any,
        val t_bank_type_check: String,
        val t_cash_amt: Any,
        val t_cash_amt_check: String,
        val t_cash_date: Any,
        val t_cash_date_check: String,
        val t_cash_desc: Any,
        val t_cash_desc_check: String,
        val t_cheque_amt: Any,
        val t_cheque_amt_check: String,
        val t_cheque_date: Any,
        val t_cheque_date_check: String,
        val t_cheque_desc: Any,
        val t_cheque_desc_check: String,
        val t_neft_amt: Any,
        val t_neft_amt_check: String,
        val t_neft_date: Any,
        val t_neft_date_check: String,
        val t_neft_desc: Any,
        val t_neft_desc_check: String,
        val t_pay_type: Any,
        val t_pay_type_check: String,
        val tax: Any,
        val tax_agent_fee_check: String,
        val tax_amount: Any,
        val tax_amount_check: String,
        val tax_check: String,
        val tax_crtm_check: String,
        val tehsill: String,
        val tehsill_check: String,
        val toolkit: Any,
        val toolkit_check: String,
        val toplink: Any,
        val toplink_check: String,
        val total_amt: Any,
        val tyre: Any,
        val tyre_check: String,
        val v_reason: Any,
        val variants: Any,
        val variants_check: String,
        val village: String,
        val village_check: String,
        val whno: String,
        val whno_check: String,
        val customer_name: String,
        val cname: String,
        val left_amt: String
    )
}
data class Newdata(
    val `data`: List<Data>,
    val error: Boolean,
    val message: String
) {
    data class Data(
        val ins_cheque_desc_check: String,
        val ins_neft_amt: Any,
        val ins_neft_amt_check: String,
        val ins_neft_date: Any,
        val ins_neft_date_check: String,
        val ins_neft_desc: Any,
        val ins_neft_desc_check: String,
        val ins_pay_type: Any,
        val ins_pay_type_check: String,
        val insurance: Any,
        val insurance_check: String,
        val insurance_com_name: Any,
        val insurance_com_name_check: String,
        val key_otp: String,
        val l_c_photo: Any,
        val l_c_photo_check: String,
        val l_sec_amt: Any,
        val l_sec_amt_check: String,
        val land_details: Any,
        val land_details_check: String,
        val lb_pb_photo: Any,
        val lb_pb_photo_check: String,
        val leftamount: String,
        val lloan_charge: Any,
        val lloan_charge_check: String,
        val lname: Any,
        val lname_check: String,
        val loan_amount: Any,
        val loan_amount_check: String,
        val loan_charge: Any,
        val loan_charge_check: String,
        val loan_next_click: String,
        val loan_section: String,
        val loans: String,
        val m_year: Any,
        val m_year_check: String,
        val make: Any,
        val make_check: String,
        val mobileno: String,
        val mobileno_check: String,
        val model: Any,
        val model_check: String,
        val model_name: Any,
        val model_name_check: String,
        val n_date: String,
        val neft_rtgs_amt: Any,
        val neft_rtgs_amt_check: String,
        val neft_rtgs_amt_word: Any,
        val neft_rtgs_date: Any,
        val neft_rtgs_date_check: String,
        val neft_rtgs_photo: Any,
        val neft_rtgs_photo_check: String,
        val new_rec: String,
        val noc: Any,
        val noc_check: String,
        val noc_photo: Any,
        val noc_photo_check: String,
        val num_plate_order: Any,
        val num_plate_order_check: String,
        val num_plate_recive: Any,
        val number_plat: Any,
        val number_plat_check: String,
        val old_t_amount: Any,
        val old_t_amount_check: String,
        val old_t_amt_word: Any,
        val old_vehicle_photo: Any,
        val old_vehicle_photo_check: String,
        val other_amt: String,
        val other_app: String,
        val other_date: String,
        val other_desc: String,
        val p_desc: Any,
        val p_desc_check: String,
        val p_photo: String,
        val p_photo_back: String,
        val p_photo_back_check: String,
        val p_photo_check: String,
        val paper_expence: Any,
        val paper_expence_check: String,
        val pinsurance: Any,
        val pinsurance_check: String,
        val policy_amount: Any,
        val policy_amount_check: String,
        val policy_date_check: String,
        val policy_number: Any,
        val policy_number_check: String,
        val product_name: String,
        val product_name_check: String,
        val r_e_name: Any,
        val r_e_name_check: String,
        val rc_book_financial: Any,
        val rc_book_update_in: Any,
        val rc_book_updatein_check: String,
        val rc_cu_img_one_check: String,
        val rc_cu_img_two_check: String,
        val rc_customer_check: String,
        val rc_fitment_check: String,
        val rc_num_plat_check: String,
        val rc_num_plat_recive_check: String,
        val rc_recive_date_check: String,
        val rc_reg_number_check: String,
        val rc_update: Any,
        val rc_update_check: String,
        val rc_update_customer: String,
        val rcbook_back: Any,
        val rcbook_back_check: String,
        val rcbook_financer_check: String,
        val recbook_photo: Any,
        val recbook_photo_check: String,
        val recive_date: Any,
        val ref_name: String,
        val ref_name_check: String,
        val ref_no: String,
        val ref_no_check: String,
        val refal_amt: Any,
        val refal_amt_check: String,
        val refal_desc: Any,
        val refal_desc_check: String,
        val refal_mobile: Any,
        val refal_mobile_check: String,
        val refal_name: Any,
        val refal_name_check: String,
        val refal_val: Any,
        val refal_val_check: String,
        val reg_no: Any,
        val reg_no_check: String,
        val register_no: Any,
        val register_no_check: String,
        val registration_num: Any,
        val rto: Any,
        val rto_check: String,
        val rto_new: Any,
        val rto_passing: Any,
        val rto_passing_check: String,
        val rto_tax: Any,
        val rto_tax_check: String,
        val sale_return: String,
        val sar_id_photo: Any,
        val sar_id_photo_check: String,
        val sectiondate: Any,
        val sectiondate_check: String,
        val sent_sms: String,
        val sign_veri: Any,
        val sign_veri_check: String,
        val skim: Any,
        val skim_amt: Any,
        val skim_check: String,
        val sms_s_date: Any,
        val sname: String,
        val sname_check: String,
        val stage: Any,
        val stage_check: String,
        val state: String,
        val state_check: String,
        val t_bank_type: Any,
        val t_bank_type_check: String,
        val t_cash_amt: Any,
        val t_cash_amt_check: String,
        val t_cash_date: Any,
        val t_cash_date_check: String,
        val t_cash_desc: Any,
        val t_cash_desc_check: String,
        val t_cheque_amt: Any,
        val t_cheque_amt_check: String,
        val t_cheque_date: Any,
        val t_cheque_date_check: String,
        val t_cheque_desc: Any,
        val t_cheque_desc_check: String,
        val t_neft_amt: Any,
        val t_neft_amt_check: String,
        val t_neft_date: Any,
        val t_neft_date_check: String,
        val t_neft_desc: Any,
        val t_neft_desc_check: String,
        val t_pay_type: Any,
        val t_pay_type_check: String,
        val tax: Any,
        val tax_agent_fee_check: String,
        val tax_amount: Any,
        val tax_amount_check: String,
        val tax_check: String,
        val tax_crtm_check: String,
        val tehsill: String,
        val tehsill_check: String,
        val toolkit: Any,
        val toolkit_check: String,
        val toplink: Any,
        val toplink_check: String,
        val total_amt: Any,
        val tyre: Any,
        val tyre_check: String,
        val v_reason: String,
        val variants: Any,
        val variants_check: String,
        val village: String,
        val village_check: String,
        val whno: String,
        val whno_check: String,
        val customer_name: String,
        val final_amt: String,
        val id: String,
        val fname: String,
        val cname: String,
        val left_amt: String
    )
}
data class New_new_count(
    val new: String
)
data class Loan_formate(
    val amt: String,
    val cdate: String,
    val desc: String,
    val empname: String,
    val msg: String,
    val otp: String,
    val success: Boolean
)