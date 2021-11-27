package airquality;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {

	private float lat;
	private float lon;
	private int uid;
	private int aqi;
	private Station station;
}
