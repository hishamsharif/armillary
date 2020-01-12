import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TAIGER employees have Defender Arcade within the company (Work hard-Play
 * hard). Every employee has provided the time (start time and finish time) when
 * he or she wants to play games.
 *
 * This utility is to calculate the minimum number of Defender Arcades needed
 * prevent overlap play time of two employees
 *
 * @author hishamsharif
 *
 */
public class DefenderArcade {

	public int countArcades(List<String> times) {

		// split the string , convert to a list of objects
		List<PlayTimeSlots> timesToObjectList = times.stream().map(PlayTimeSlotsFactory::create)
				.collect(Collectors.toList());

		// Traverse thru the list of time slots, keep track of not finished items, and
		// as well as the max count
		ArrayList<Integer> prevUnfinishedPlayersList = new ArrayList<>();
		int maxConcurrentUsersPlayed = 0;

		for (PlayTimeSlots nextPlayer : timesToObjectList) {
			int nextPlayerStartedAt = nextPlayer.getStartTime();
			int nextPlayerEndedAt = nextPlayer.getEndTime();
			// System.out.println("Start/End-Times: "+nextPlayer.toString());

			// Iterate thru the list of employees still playing
			Iterator<Integer> iterator = prevUnfinishedPlayersList.iterator(); // it will return iterator
			while (iterator.hasNext()) {
				int prevUnfinishedPlayerEndsAt = iterator.next();

				// if found any previous user has ended, then remove from the prev. unfinished
				// list
				if (prevUnfinishedPlayerEndsAt <= nextPlayerStartedAt) {
					iterator.remove();
				}
			}

			// Add this slots end time to the prev unifinsed list
			prevUnfinishedPlayersList.add(nextPlayerEndedAt);

			// if the no of currently playing users are greater last max no, then set the
			// max to no of employees currently playing
			if (prevUnfinishedPlayersList.size() > maxConcurrentUsersPlayed) {
				maxConcurrentUsersPlayed++;
			}

		}

		return maxConcurrentUsersPlayed;

	}

	static class PlayTimeSlotsFactory {

		static PlayTimeSlots create(String timeStr) {
			String[] s = timeStr.split(" ");
			int startTime = Integer.valueOf(s[0]);
			int endTime = Integer.valueOf(s[1]);

			return new PlayTimeSlots(startTime, endTime);
		}
	}

	static class PlayTimeSlots {

		private int startTime;
		private int endTime;

		public PlayTimeSlots(int startTime, int endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
		}

		public int getStartTime() {
			return startTime;
		}

		public void setStartTime(int startTime) {
			this.startTime = startTime;
		}

		public int getEndTime() {
			return endTime;
		}

		public void setEndTime(int endTime) {
			this.endTime = endTime;
		}

		@Override
		public String toString() {
			return startTime + " " + endTime;
		}
	}

}
