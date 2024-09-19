@RestController
@RequestMapping("/api/posts")
PublicController

@GetMapping
public ResponseEntity<List<PostResponse>> getAllPosts();

@GetMapping("/{subject}")
public ResponseEntity<List<PostResponse>> getPostsBySubject();

@RestController
@RequestMapping("/api/auth")
AuthController

@PostMapping("/register")
public ResponseEntity<UserRegistrationResponse>  userSelfRegistration(@RequestBody NewUserRequest request);

@PostMapping("/authenticate")
public ResponseEntity<LoginResponse> authenticate (@RequestHeader(value = "Authorization") String authorizationHeader);


@RestController
@RequestMapping("/api/posts")
PostsController

@PostMapping
public ResponseEntity<PostResponse> addNewPost(NewPostRequest request);

@PutMapping
public ResponseEntity<PostResponse> updatePost(PostUpdateRequest request);

@DeleteMapping ("/{id}")
public ResponseEntity<DeleteResponse> deletePost(@PathVariable Long id);

@RestController
@RequestMapping("/api/users")
UsersController

@PutMapping
public ResponseEntity<UserDataResponse> updateUserData(@RequestBody UserUpdateRequest request);

