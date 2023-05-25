<<<<<<< HEAD:src/main/java/com/poc1/model/UserRole.java
package com.poc1.model;
=======
 package com.poc1.Entity;
>>>>>>> cf7145f0dabce11b8351b1876f6542778878ce5b:src/main/java/com/poc1/Entity/UserRole.java

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

	@Id
	private String email;
	private String password;
	private String role;
	
	
	
}
