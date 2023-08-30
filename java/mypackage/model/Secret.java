package mypackage.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.Data;

import mypackage.enumeration.Gender;
import mypackage.model.Feedback;

//@Getter @Setter 
@NoArgsConstructor //generates getter setter and public constructor without any arguments 
@Data //generates getter setter without any constructors and toString() like codes
//@ToString

@Entity
@Table(name = "secret", uniqueConstraints =
{
		@UniqueConstraint(columnNames = {"un"}, name = "UK_SECRET_UN")
}
)
//@DynamicInsert
//@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //@JsonIgnoreProperties() can be used only in class whereas @JsonIgnore can be used only in fields
public class Secret implements Serializable//primary table
{
	private static final long serialVersionUID = 1L;

	@Id //used for primary key
	@GeneratedValue(strategy = GenerationType.AUTO)//generator = "autogen") //IDENTITY is used for all tables //generation types are IDENTITY, SEQUENCE, TABLE and AUTO //AUTO automatically selects the generation type suitable for the table //GenerationType is a enumeration class which has enum class IDENTITY hence class naming convention is used for GenerationType
	//@TableGenerator(name = "autogen", initialValue = 0, allocationSize = 1) //used for GenerationType.TABLE //sequence_name and next_val field values are default and 0
	@Column(name = "id")
	Long id; //id is always Long everywhere //Long wrapper class recommended //Integer, Double and Long wrapper classes has an advantage that it allows to compare with null whereas integer, double and long can't
	
	@Version
	Long versionLock;
	
	@Size(min = 6, max = 50) //preferred only if min length is required max is optional
	@Column(name = "un") //, unique = true) //can be used for unique key but with auto-generated name
	String un; //private by default //private can also be used
	
	@Size(min = 6, max = 15)
	@Column(name = "pwd")
	String pwd;
	
	@NotNull
	@Column(name = "first_name", length = 30, nullable = false) //preferred only if max length is required
	String firstName;
	
	@NotNull
	@Column(name = "last_name")
	String lastName;
	
	@Column(name = "dob")
	Date dob;
	
	@Column(name = "gender")
	@Enumerated(EnumType.STRING) //used only for limited known options
	Gender gender;
	
	@Column(name = "doc")
	Date doc; //dateOfCreation //keep minimum variable length
	
	//@Null //default //@Null can also be used
	@Column(name = "mobile")
	String mobile; //String is used as it has no value//Long is recommended instead of Integer for numbers
	
	@OneToMany(mappedBy = "secret", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true) //cascade = CascadeType.ALL orphanRemoval allows to remove individual elements from the secondary table
	@JsonManagedReference
	List<Note> notes;
	
	@OneToOne(mappedBy = "secret", fetch = FetchType.LAZY, cascade = CascadeType.ALL) //if the particular record in the primary table is deleted the secondary table table associated with it is also deleted by cascading the secondary table to the primary table by cascade effect //cascade type should be mentioned in both primary and secondary table
	Feedback feedback;
	
	/*@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "FK_SECRET_CITY"))
	City city;*/
	
	/*@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "FK_SECRET_COURSE"))
	List<Course> courses;*/
	
	//@Email
	//String email;
	
	@Transient //works within class manipulations not as a column in database
	String fullName;
	
	public String getFullName()
	{
		String fullName = "";
		if(firstName != null)
		{
			fullName = firstName;
		}
		if(firstName != null && lastName != null)
		{
			fullName = firstName+" "+lastName;
		}
		return fullName;
	}
	
	/*public Secret() //Constructor not needed @NoArgsConstructor can be used instead
	{
		
	}*/
	
	/*public Secret(String un)
	{
		this.un = un;
	}*/
	
	/*public String toString()
	{
		String info = "Secret";
		//String info = "User Name : "+this.un+" feedback is : "+this.feedback.getMsg(); //throws NullPointerException if the variable is null for an unique object-address of the class (entity)
		return info;
	}*/
}