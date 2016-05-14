package com.example.genceozer.utodo;

import com.example.genceozer.utodo.entities.Task;
import com.example.genceozer.utodo.entities.TaskGroup;
import com.example.genceozer.utodo.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.genceozer.utodo.login_register.LoginActivity;
import com.example.genceozer.utodo.login_register.RegisterActivity;
import com.example.genceozer.utodo.taskgroup.TaskGroupActivity;
import com.firebase.client.*;
import android.app.Dialog;
import android.util.Log;


//Singleton class
public class Connector{
    //Delegates******
    public interface ConnectorLogin{
        public void userLoggedIn();
        public void userLogInFailed();
    }
    public LoginActivity loginActivityDelegate = new LoginActivity();

    public interface ConnectorSignUp{
        public void userSignedUp();
        public void popSignUpError();
    }
    public RegisterActivity registerActivityDelegate = new RegisterActivity();

    public interface ConnectorTaskGroup{
        public void taskLoaded(TaskGroup taskGroup);
    }
    public TaskGroupActivity taskGroupDelegate = new TaskGroupActivity();

    //End Delegates******


    private static Connector sharedInstance = new Connector();

	Firebase rootRef = new Firebase("https://vivid-inferno-9165.firebaseio.com");

	public User loggedUser = new User(); //Update at log in

	private Connector(){}

	public static Connector getInstance( ) {
      return sharedInstance;
   }

//User Methods
   public void getAllUsers(){
   		rootRef.child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class); //May not work properly. Add "public User(){}" empty constructer to Users.java if fails.
                //Invoke necessary methods.
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
   		});
   }

   public void getUser(String username){
   		rootRef.child("users/" + username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class); //Add User.java a constructor which takes a Map as parameter.
                //Invoke necessary methods.
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
   }

   public void userSignUp(final String username, final String email, final String password){
   		rootRef.child("users/" + username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Log.i("Dev", "Table changed");
                if (dataSnapshot.getValue() != null) {
                    System.out.println("A user with user name: " + username + " already exists.");
                } else {
                    rootRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result) {
                            System.out.println("User with username \"" + username + "\" has been created.");

                            User newUser = new User(result.get("uid"), username, email);
                            rootRef.child("users/" + username).setValue(newUser);

                            registerActivityDelegate.userSignedUp();
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            System.out.println(firebaseError);

                            registerActivityDelegate.popSignUpError();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
   }

   public void userLogIn(final String username, final String password){ //Username parameter removed
       rootRef.child("users/" + username).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(final DataSnapshot dataSnapshot) {
               User user = dataSnapshot.getValue(User.class);
               System.out.println(user.getEmail());
               //Invoke necessary methods.
               final String email = user.getEmail();

               if (dataSnapshot.getChildrenCount() > 0){
                   rootRef.authWithPassword(email, password, new Firebase.AuthResultHandler(){
                       @Override
                       public void onAuthenticated(AuthData authData){
                           loggedUser.setEmail(email);
                           loggedUser.setUsername(username);
                           loggedUser.setUserId(dataSnapshot.getKey());
                           //Invoke methods after log in.
                           loginActivityDelegate.userLoggedIn();
                       }

                       @Override
                       public void onAuthenticationError(FirebaseError error){
//                final Dialog dialog = new Dialog(LoginActivity.this); // Context, this, etc.
//                dialog.setContentView(R.layout.dialogdemo);
//                dialog.setTitle(R.string.dialog_title);
//                dialog.show();
                           loginActivityDelegate.userLogInFailed();
                           System.out.println("Something went wrong while logging in.");
                       }
                   });
               }
               else{
                   loginActivityDelegate.userLogInFailed();
               }
           }

           @Override
           public void onCancelled(FirebaseError firebaseError) {

           }
       });
   }

//Group Methods
   public void createGroup(String groupName){ //Add members and tasks later.
	   	Firebase postRef = rootRef.child("groups");

	   	Map<String, String> post = new HashMap<String, String>();
	   	post.put("groupname", groupName);
	   	postRef.push().setValue(post);
   }

   public void getGroup(String groupID){
      rootRef.child("groups/" + groupID).addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
              List<String> tempList = new ArrayList<>();
              for (DataSnapshot postSnapshot : dataSnapshot.child("members").getChildren()){
                  tempList.add(postSnapshot.getValue().toString());
              }

              TaskGroup tGroup = new TaskGroup();
              tGroup.setGroupTitle(dataSnapshot.child("groupTitle").getValue().toString());
              tGroup.setMembers(tempList);
              taskGroupDelegate.taskLoaded(tGroup);
          }

          @Override
          public void onCancelled(FirebaseError firebaseError) {

          }
      });
   }

    public void getAllGroups(final String username){
        rootRef.child("users/" + username + "/groupList").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot1, String s) {
                rootRef.child("users/" + username + "/groupList/" + dataSnapshot1.getKey()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot2) {
                        getGroup(dataSnapshot2.getKey());
                        Log.i("DEV", dataSnapshot2.getKey());
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }

                //Invoke necessary methods.


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

   public void getTasks(String groupID){
      rootRef.child("groups/" + groupID + "tasks").addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
              Task newTask = new Task((Map<String, Object>) dataSnapshot.getValue()); //Constructor with HashMap. Resolve.
              //Invoke necessary methods.
          }

          @Override
          public void onCancelled(FirebaseError firebaseError) {

          }
      });
   }

   public void inviteUser(String groupID, String groupName, String username){
   		Firebase postRef = rootRef.child("users/" + username + "/invitations");

   		Map<String, String> post = new HashMap<String, String>();
   		post.put("gid", groupID);
   		post.put("groupname", groupName);
   		post.put("invitor", loggedUser.getUsername());

       postRef.push().setValue(post);
   }

    public void addParticipant(String groupID, String groupname, String username){
        Firebase postRef = rootRef.child("groups/" + groupID + "/members");
        postRef.push().setValue(username);

        postRef = rootRef.child("users/" + username + "/groupList/" + groupID);
        postRef.setValue(groupname);
    }

    public void createTaskGroup(final TaskGroup taskGroup, final List<String> contributers){

        Firebase postRef = rootRef.child("groups");
        Map<String, String> post = new HashMap<String, String>();
        post.put("groupTitle",taskGroup.getGroupTitle());

        postRef.push().setValue(post, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError != null) {
                    System.out.println("Data could not be saved. " + firebaseError.getMessage());
                } else {
                    System.out.println("Data saved successfully.");
                    addParticipant(firebase.getKey(), taskGroup.getGroupTitle(),loggedUser.getUsername());

                    for (String username : contributers ){
                        inviteUser(firebase.getKey() , taskGroup.getGroupTitle(), username);
                    }
                }
            }
        });



    }

   public void createTask(String groupID, String title, String desc){
   		Firebase postRef = rootRef.child("groups/" + groupID);

   		Map<String, Object> post = new HashMap<String, Object>();
   		//Get current date. post.put("timestamp", date)
   		post.put("title", title);
   		post.put("desc", desc);

   		postRef.push().setValue(post);

   }

   public void createSubtask(String groupID, String title, String desc){
   		Firebase postRef = rootRef.child("groups/" + groupID + "/subtasks");

   		Map<String, Object> post = new HashMap<String, Object>();
   		//Get current date. post.put("timestamp", date)
   		post.put("title", title);
   		post.put("desc", desc);

   		postRef.push().setValue(post);
   }
}
