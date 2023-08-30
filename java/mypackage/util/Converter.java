package mypackage.util;

import mypackage.model.Feedback;
import mypackage.model.Note;
import mypackage.model.Secret;

import java.util.ArrayList;
import java.util.List;

import mypackage.dto.FeedbackDto;
import mypackage.dto.NoteDto;
import mypackage.dto.SecretDto;

public class Converter
{
	public static SecretDto toSecretDto(Secret secret)
	{
		SecretDto secretDto = new SecretDto();
		secretDto.setId(secret.getId());
		secretDto.setUn(secret.getUn());
		secretDto.setPwd(secret.getPwd());
		secretDto.setFirstName(secret.getFirstName());
		secretDto.setLastName(secret.getLastName());
		secretDto.setDob(secret.getDob());
		secretDto.setGender(secret.getGender());
		secretDto.setDoc(secret.getDoc());
		secretDto.setMobile(secret.getMobile());
		
		/*List<NoteDto> noteDtos = new ArrayList<NoteDto>();
		if(secret.getNotes() != null)
		{
			for(Note note : secret.getNotes())
			{
				noteDtos.add(noteMtd(note));
				secretDto.setNoteDTOs(noteDtos);
			}
		}*/
		
		/*if(secret.getFeedback() != null)
			secretDto.setFeedbackDto(toFeedbackDto(secret.getFeedback()));*/
		
		return secretDto;
	}
	
	public static Secret toSecret(SecretDto secretDto)
	{
		Secret secret = new Secret();
		secret.setId(secretDto.getId());
		secret.setUn(secretDto.getUn());
		secret.setPwd(secretDto.getPwd());
		secret.setFirstName(secretDto.getFirstName());
		secret.setLastName(secretDto.getLastName());
		secret.setDob(secretDto.getDob());
		secret.setGender(secretDto.getGender());
		secret.setDoc(secretDto.getDoc());
		secret.setMobile(secretDto.getMobile());
		
		/*List<Note> notes = new ArrayList<Note>();
		if(secretDto.getNoteDtos() != null)
		{
			for(NoteDto noteDto : secretDTO.getNoteDtos())
			{
				notes.add(toNote(noteDto));
			}
			secret.setNotes(notes);
		}*/
		
		/*if(secretDto.getFeedbackDto() != null)
			secret.setFeedback(toFeedback(secretDto.getFeedbackDto()));*/
		
		return secret;
	}
	
	public static FeedbackDto toFeedbackDto(Feedback feedback)
	{
		FeedbackDto feedbackDto = new FeedbackDto();
		feedbackDto.setId(feedback.getId());
		feedbackDto.setMsg(feedback.getMsg());
		return feedbackDto;
	}
	
	public static Feedback toFeedback(FeedbackDto feedbackDto)
	{
		Feedback feedback = new Feedback();
		feedback.setId(feedbackDto.getId());
		feedback.setMsg(feedbackDto.getMsg());
		return feedback;
	}
	
	public static NoteDto toNoteDto(Note note)
	{
		NoteDto noteDto = new NoteDto();
		noteDto.setId(note.getId());
		noteDto.setMsg(note.getMsg());
		return noteDto;
	}
	
	public static Note toNote(NoteDto noteDto)
	{
		Note note = new Note();
		note.setId(noteDto.getId());
		note.setMsg(noteDto.getMsg());
		return note;
	}	
}