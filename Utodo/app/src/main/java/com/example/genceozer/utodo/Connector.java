package com.example.genceozer.utodo;

import com.example.genceozer.utodo.entities.Task;
import com.example.genceozer.utodo.entities.User;
import java.util.HashMap;
import java.util.Map;
import com.firebase.client.*;

//Singleton class
public class Connector{
	private static Connector sharedInstance = new Connector();

	Firebase rootRef = new Firebase("https://docs-examples.firebaseio.com/web/data");

	User loggedUser = new User(); //Update at log in

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
                if (dataSnapshot.getValue() != null) {
                    System.out.println("A user with user name: " + username + " already exists.");
                }
                else{
                    rootRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>(){
                        @Override
                        public void onSuccess(Map<String, Object> result){
                            System.out.println("User with username \"" + username + "\" has been created.");

                            User newUser = new User(dataSnapshot.getKey(), username, null, email); //We do not need to know User passwords.
                            rootRef.child("users").setValue(newUser);
                        }
                        @Override
                        public void onError(FirebaseError firebaseError) {
                            System.out.println("Error while signing up.");
                        }
                    });
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
   		});
   }

   public void userLogIn(String email, String password){ //Username parameter removed
   		rootRef.authWithPassword(email, password, new Firebase.AuthResultHandler(){
			@Override
			public void onAuthenticated(AuthData authData){
				System.out.println("User logged in.");
				//Invoke methods after log in.
			}

			@Override
			public void onAuthenticationError(FirebaseError error){
				System.out.println("Something went wrong while logging in.");
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
   		Firebase postRef = rootRef.child("/" + username + "/invitations");

   		Map<String, String> post = new HashMap<String, String>();
   		post.put("gid", groupID);
   		post.put("groupname", groupName);
   		post.put("invitor", username);

   		postRef.push().setValue(post);
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
