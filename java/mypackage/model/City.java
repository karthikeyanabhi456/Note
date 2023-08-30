package mypackage.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "city", uniqueConstraints =
{
		@UniqueConstraint(columnNames = {"name"}, name = "UK_CITY_NAME")
}
)
//@DynamicInsert
//@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class City implements Serializable
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
}