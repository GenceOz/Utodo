package com.example.genceozer.utodo;

import com.example.genceozer.utodo.entities.Invitation;
import com.example.genceozer.utodo.entities.SubTask;
import com.example.genceozer.utodo.entities.Task;
import com.example.genceozer.utodo.entities.TaskGroup;
import com.example.genceozer.utodo.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.genceozer.utodo.invitations.InvitationActivity;
import com.example.genceozer.utodo.login_register.LoginActivity;
import com.example.genceozer.utodo.login_register.RegisterActivity;
import com.example.genceozer.utodo.subtask.SubtaskActivity;
import com.example.genceozer.utodo.task.CreateTasks;
import com.example.genceozer.utodo.task.TaskActivity;
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
        public void taskLoaded(TaskGroup taskGroup, Object gid);
    }
    public TaskGroupActivity taskGroupDelegate = new TaskGroupActivity();

    public interface ConnectorCreateTask{
        public void taskCreated();
    }
    public CreateTasks createTaskDelegate = new CreateTasks();

    public interface ConnectorTask{
        public void taskLoaded(Task task, Object tid);
    }
    public TaskActivity taskActivityDelegate = new TaskActivity();

    public interface ConnectorSubtask{
        public void subTaskLoaded(SubTask subTask, Object stid);
    }
    public SubtaskActivity subTaskActivityDelegate = new SubtaskActivity();

    public interface ConnectorInvitation{
        public void invitationLoaded(Invitation invitation, Object stid);
    }
    public InvitationActivity invitationActivityDelegate = new InvitationActivity();
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
               //Invoke necessary methods.
               final String email = dataSnapshot.child("email").getValue().toString();

               if (dataSnapshot.getChildrenCount() > 0) {
                   rootRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                       @Override
                       public void onAuthenticated(AuthData authData) {
                           loggedUser.setEmail(email);
                           loggedUser.setUsername(username);
                           loggedUser.setUserId(dataSnapshot.getKey());

                           List<Object> tempList = new ArrayList<>();
                           for (DataSnapshot postSnapshot : dataSnapshot.child("invitations").getChildren()) {
                               tempList.add(postSnapshot.getValue());
                           }
                           loggedUser.setInvitations(tempList);
                           //Invoke methods after log in.
                           loginActivityDelegate.userLoggedIn();
                       }

                       @Override
                       public void onAuthenticationError(FirebaseError error) {
//                final Dialog dialog = new Dialog(LoginActivity.this); // Context, this, etc.
//                dialog.setContentView(R.layout.dialogdemo);
//                dialog.setTitle(R.string.dialog_title);
//                dialog.show();
                           loginActivityDelegate.userLogInFailed();
                           System.out.println("Something went wrong while logging in.");
                       }
                   });
               } else {
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

   public void getGroup(final String groupID){
      rootRef.child("groups/" + groupID).addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
              List<String> tempList = new ArrayList<>();
              for (DataSnapshot postSnapshot : dataSnapshot.child("members").getChildren()) {
                  tempList.add(postSnapshot.getValue().toString());
              }

              TaskGroup tGroup = new TaskGroup();
              tGroup.setGroupTitle(dataSnapshot.child("groupTitle").getValue().toString());
              tGroup.setMembers(tempList);

              taskGroupDelegate.taskLoaded(tGroup, groupID);
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
      rootRef.child("groups/" + groupID + "/tasks").addChildEventListener(new ChildEventListener() {
          @Override
          public void onChildAdded(DataSnapshot dataSnapshot, String s) {
              Task newTask = new Task();
              newTask.setDescription(dataSnapshot.child("desc").getValue().toString());
              newTask.setTitle(dataSnapshot.child("title").getValue().toString());
              newTask.setIsDone(((Boolean) dataSnapshot.child("isDone").getValue()));
              newTask.setDueDate(dataSnapshot.child("date").getValue().toString());
              taskActivityDelegate.taskLoaded(newTask, dataSnapshot.getKey());
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

    public void getSubTask(String groupID, String taskID){
        rootRef.child("groups/" + groupID + "/tasks/" + taskID + "/subtasks").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SubTask newSubTask = new SubTask();
                newSubTask.setDescription(dataSnapshot.child("description").getValue().toString());
                newSubTask.setTitle(dataSnapshot.child("title").getValue().toString());
                newSubTask.setIsDone(((Boolean) dataSnapshot.child("done").getValue()));
                subTaskActivityDelegate.subTaskLoaded(newSubTask, dataSnapshot.getKey());
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
        post.put("groupTitle", taskGroup.getGroupTitle());

        postRef.push().setValue(post, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError != null) {
                    System.out.println("Data could not be saved. " + firebaseError.getMessage());
                } else {
                    System.out.println("Data saved successfully.");
                    addParticipant(firebase.getKey(), taskGroup.getGroupTitle(), loggedUser.getUsername());

                    for (String username : contributers) {
                        inviteUser(firebase.getKey(), taskGroup.getGroupTitle(), username);
                    }
                }
            }
        });



    }

   public void createTask(String groupID, Task task){
   		Firebase postRef = rootRef.child("groups/" + groupID + "/tasks");
   		Map<String, Object> post = new HashMap<String, Object>();
   		//Get current date. post.put("timestamp", date)
   		post.put("title", task.getTitle());
   		post.put("desc", task.getDescription());
        post.put("isDone", task.isDone());
       post.put("date",task.getDueDate());
        Firebase ref = postRef.push();
   		ref.setValue(post);

       createSubtask(groupID, task.getSubTasks(),ref.getKey());

   }

   public void createSubtask(String groupID, List<SubTask> subtasks, String taskId){
       Firebase postRef = rootRef.child("groups/" + groupID + "/tasks/"+ taskId+"/subtasks");

       for (SubTask st : subtasks){
           postRef.push().setValue(st);
       }
   }

    public void getUserInvitations(){
        rootRef.child("users/" + loggedUser.getUsername() + "/invitations").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Invitation invitation = new Invitation();
                invitation.setGroupTitle(dataSnapshot.child("groupname").getValue().toString());
                invitation.setInvitorName(dataSnapshot.child("invitor").getValue().toString());
                invitationActivityDelegate.invitationLoaded(invitation, dataSnapshot.getKey());
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
}
