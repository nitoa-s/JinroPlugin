package com.github.nitoa_s.JinroPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JinroVoteManager {
	private Map<JinroJoinPlayer, Integer> voteData = new HashMap<JinroJoinPlayer, Integer>(1);
	private Map<JinroJoinPlayer, JinroJoinPlayer> isVotedPlayers = new HashMap<JinroJoinPlayer, JinroJoinPlayer>(1);
	private JinroJoinPlayer[] lockPlayer = null;

	public void addVote(JinroJoinPlayer isVotedPlayer, JinroJoinPlayer targetPlayer) {
		voteData.put(targetPlayer, voteData.containsKey(targetPlayer) ? voteData.get(targetPlayer) + 1 : 1);
		isVotedPlayers.put(isVotedPlayer, targetPlayer);
	}

	public boolean isVotedFlag(JinroJoinPlayer player) {
		return isVotedPlayers.containsKey(player);
	}

	public boolean hasLockedPlayer(JinroJoinPlayer player) {
		if( lockPlayer == null ) return true;
		for( int i = 0; i < lockPlayer.length; i++ )
			if( lockPlayer[i].equals(player) ) return true;
		return false;
	}

	public void reset() {
		voteData = new HashMap<JinroJoinPlayer, Integer>(1);
		isVotedPlayers = new HashMap<JinroJoinPlayer, JinroJoinPlayer>(1);
		lockPlayer = null;
	}

	public JinroJoinPlayer[] getMostPlayer() {
		ArrayList<JinroJoinPlayer> mostPlayers = new ArrayList<JinroJoinPlayer>(1);
		int max = 0;
		for(JinroJoinPlayer votePlayer: voteData.keySet())
			if( max < voteData.get(votePlayer) ) {
				mostPlayers.clear();
				max =  voteData.get(votePlayer);
				mostPlayers.add(votePlayer);
			} else if( max == voteData.get(votePlayer) ) {
				mostPlayers.add(votePlayer);
			}
		JinroJoinPlayer[] targetPlayers = new JinroJoinPlayer[mostPlayers.size()];
		mostPlayers.toArray(targetPlayers);
		lockPlayer = targetPlayers;
		return targetPlayers;
	}

	public String[] voteText() {
		String[] voteText = new String[isVotedPlayers.size()];
		int index =0;
		for(JinroJoinPlayer joinPlayer: isVotedPlayers.keySet()) {
			voteText[index] = joinPlayer.getPlayer().getDisplayName() + " ---> " + isVotedPlayers.get(joinPlayer).getPlayer().getDisplayName();
			index++;
		}
		return voteText;
	}


}
