package mypackage.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data

@Entity
@Table(name = "course", uniqueConstraints =
{
		@UniqueConstraint(columnNames = {"name"}, name = "UK_COURSE_NAME")
}
)
//@DynamicInsert
//@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	
	@Version
	Long versionLock;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "fee")
	Double fee; //Double is used as it has value //Double is recommended instead of Integer for numbers as it has an advantage of also taking decimal values whereas Integer can't
	
	/*@ManyToMany
	@JoinColumn(name = "secret_id", foreignKey = @ForeignKey(name = "FK_COURSE_SECRET"))
    List<Secret> secrets;*/
	
	/*public Course(String name)
	{
		this.name = name;
	}*/
}