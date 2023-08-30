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
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data

@Entity
@Table(name = "note")
//@DynamicInsert
//@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Note implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	
	@Version
	Long versionLock;
	
	@Column(name = "msg")
	String msg;
	
	//@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	@JoinColumn(name = "secret_id", foreignKey = @ForeignKey(name = "FK_SECRET_NOTE")) //any name can be given to @JoinColumn name
	Secret secret;
	
	/*public Note(String msg, Secret secret)
	{
		this.msg = msg;
		this.secret = secret;
	}*/
	
	/*public String toString()
	{
		String info = "Note";
		//String info = "Note : "+this.msg+" belongs to the User Name : "+this.secret.getUn();
		return info;
	}*/
}