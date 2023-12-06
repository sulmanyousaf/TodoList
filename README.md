# TodoList

Todo-List App (online & Offline)

Backend
Am using mockfly as my backend to get data from online database.
Base_URL = “https://app.mockfly.dev/65704543ed307e001b0f2a35”
End_Point = “todoData”
Functionalities
Remote Database: Use to fetch data
Room Database: insert, update and delete local entries.

Languages
Using Kotlin & XML.

Working Modes
This app works on both way i.e. online & offline.

Online:
App need to be connected to Internet to fetch the data.

Offline:
App would get data from Room Database.

How to use the App?
When app got open there would be a title at Top and RecyclerView below that who list the Data that we fetch from the Remote Database or from Local Room Database.
There is also a Floating button at bottom right. By clicking this button floating button user would be redirect to another fragment where user can add new task to the Todo-List.
In next fragment User can add title & description for the new task & by clicking add Task button user can add the task to Local Room Database.

App Code Details
There is a MainActivity and two fragments. MainActivity has a FragmentContainerView and using NavigationMapController to switch between both fragments.
First Fragment is using to Add and Update the task.
FirstFragmentVM that has a Repository Instance injected in its’s constructor and has two functions insert and update that are launching the viewModelScope Coroutine and calling the suspend function to insert and update the task.
Second Fragment is to show the List of all task.
SecondFragmentVM that has a Repository instance injected in it’s constructor and has two init block to initiate the onlineData function and observerDataFromDao function. The onlineData function fetch the data from the Remote Database and store the result in the Room Database. The ObserverDataFromDao get the data from Room Databse and set it to the MutablestateFlow variable and that would be collected in the SecondFragment. The delete function is used to delete the task from the Room Database.
SecondFragmentAdapter is used to inflate the data to the layout and there are two callbacks onItemClicked, onDeleteClicked send the item which is clicked to the SecondFragment. onItemClicked will receive that item and open it in update mode in FirstFragment and onDeleteClicked will receive the item and delete it from Room Database
There is an abstract class naming BaseFragmentVB which is inflating the viewbinding to the fragment.
There is an abstract class naming BaseFragmentBP which is extending the BaseFragmentVB and inflating the back press for the fragment and passing the viewbinding to the BaseFragmentVB.
There is an abstract class naming BaseFragmentVM which is extending the BaseFragmentBP and inflating the view model for the fragment and passing the back press & viewbinding to the BaseFragmentBP.
There is an abstract class naming BaseFragmentVMBP which is extending the BaseFragmentVM and inflating the back press for the fragment and passing the rest to the BaseFragmentVM.

BaseFragmentVMBP is used when we need to inflate Back Press, View Model & View Binding to the fragment.
BaseFragmentBP is used when we need to inflate Back Press & View Binding to the fragment.

BaseViewModel has three functions that has three functions showLoader, hideLoader and SendError.

There are three extensions files ExtFragment that has progress dialog, navigation back and observer for progress and error message, ExtMisc that has function to set the visibility of the AlertDialog, showToast is to show the toast in fragment.

TodoRepo has following functions onllineData(send a safeApiCall to get the data from the Remote Database), insert (add the item to the Room Database with Conflictstratergy of Replacing the item if it already exist with the same PrimaryKey which is id), insertOrUpdateTodos (add the list of the Items to the Room Database), update (update the record from the Room Database), delete (delete the record from the Room Database).

AppModule is the object that following instances TodoDatabse, TodoDao, TodoRepo, OkHttpClient, Retrofit and TodoService.

TodoDatabase has an abstract function of todoDaao.

TodoService has a suspend function that return the TodoResponse.
TodoResponse has a List of TodoModel and TodoModel has a table naming task_table and primarykey id and column like title, description and the creationDate.

safeApiCall is the inline lambda return function which is returning the flow of general type data T. The flow is emitting the data and error to error handling.

Result is a sealed class with data classes Success and Error.

ApiName is the object that has constant variables base url and end point.

TodoDao has following functions onllineData(send a safeApiCall to get the data from the Remote Database), insert (add the item to the Room Database with Conflictstratergy of Replacing the item if it already exist with the same PrimaryKey which is id), insertOrUpdateTodos (add the list of the Items to the Room Database Database with Conflictstratergy of Replacing the item if it already exist with the same PrimaryKey which is id), update (update the record from the Room Database), delete (delete the record from the Room Database).
