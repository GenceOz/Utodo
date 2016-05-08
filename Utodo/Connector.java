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
   			public void onChildAdded(DataSnapshot snapshot, String previousChildKey){
   				User user = snapshot.getValue(User.class); //May not work properly. Add "public User(){}" empty constructer to Users.java if fails.
   				//Invoke necessary methods.
   			}

   		});
   }

   public void getUser(String username){
   		rootRef.child("users/" + username).addValueEventListener(new ValueEventListener()){
   			@Override
   			public void onDataChange(DataSnapshot snapshot){
   				User user = snapshot.getValue(User.class); //Add User.java a constructor which takes a Map as parameter.
   				//Invoke necessary methods.
   			}

   		}
   }

   public void userSignUp(String username, String email, String password){
   		rootRef.child("users/" + username).addValueEventListener(new ValueEventListener()){
   			@Override
   			public void onDataChange(DataSnapshot snapshot){
   				if (snapshot.getValue() != null) {
   					System.out.println("A user with user name: " + this.username + " already exists.");
   				}
   				else{
   					rootRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>)(){
   						@Override
   						public void onSuccess(Map<String, Object> result){
   							System.out.println("User with username \"" + username + "\" has been created.");
   						}

   						User newUser = new User(result.get("uid"), username, null, email); //We do not need to know User passwords.

   						rootRef.child("users").setValue(newUser);
   					}
   				}
   			}
   		}
   }

   public void userLogIn(String username, String email, String password){
   		rootRef.authWithPassword(email, password, new Firebase.AuthResultHandler(){
			@Override
			public void onAuthentication(AuthData authData){
				System.out.println("User logged in.");
				//Invoke methods after log in.
			}

			@Override
			public void onAuthenticationError(FirebaseError error){
				System.out.println("Something went wrong while logging in.");
			}
		})
   }

//Group Methods
   public void createGroup(String groupName){ //Add members and tasks later.
	   	Firebase postRef - rootRef.child("groups");

	   	Map<String, String> post = new HashMap<String, String>();
	   	post.put("groupname", groupName);
	   	postRef.push().setValue(post);
   }

   public void getGroup(String groupID){
      rootRef.child("groups/" + groupID).addValueEventListener(new ValueEventListener()){
            @Override
            public void onDataChange(DataSnapshot snapshot){
               //Add group class and construct group with HashMap
               //Invoke necessary methods.
            }

      }
   }

   public getTasks(String groupID){
      rootRef.child("groups/" + groupID + "tasks").addValueEventListener(new ValueEventListener()){
            @Override
            public void onDataChange(DataSnapshot snapshot){
               Task newTask = new Task(snapshot.getValue()) //Constructor with HashMap
               //Invoke necessary methods.
            }

         }
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

   		Map<String, String> post = new HashMap<String, Object>();
   		//Get current date. post.put("timestamp", date)
   		post.put("title", title);
   		post.put("desc", desc);

   		postRef.push().setValue(post);

   }

   public void createSubtask(String title, String desc){
   		Firebase postRef = rootRef.child("groups/" + groupID + "/subtasks");

   		Map<String, String> post = new HashMap<String, Object>();
   		//Get current date. post.put("timestamp", date)
   		post.put("title", title);
   		post.put("desc", desc);

   		postRef.push().setValue(post);
   }
}