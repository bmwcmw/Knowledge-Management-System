package com.bmwcmw.km.common.objects;


/**
 * <p>A Long pair contains two long values : Subject and Object</p>
 * 
 * @author CMWT420
 *
 */
public class SOLongPair {

	private Long _subject;
	private Long _object;
	
	/**
	 * <p>Create a Long pair directly using two longs.</p>
	 */
	public SOLongPair(Long subject, Long object){
		_subject = subject;
		_object = object;
	}
	
	public Long getSubject() {
		return _subject;
	}

	public Long getObject() {
		return _object;
	}
	
	/**
	 * @return S and O separated by whitespace
	 */
	public String toString(){
		return _subject.toString() + " " + _object.toString();
	}
	
}
