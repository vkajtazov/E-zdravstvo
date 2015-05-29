package mk.ukim.finki.ezdravstvo.model;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "time_slots")
public class TimeSlots extends BaseEntity{

	private Time startTime;
	
	private Time endTime;

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return String.format("[ID = %s, stTime = %s, endTime = %s]\n",id ,startTime, endTime);
	}
	
	
}
