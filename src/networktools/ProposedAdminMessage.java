/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package networktools;

import temperaturemonitoring.TemperatureNode;

/**
 *
 * @author krc
 */
public class ProposedAdminMessage extends Message {

    private int proposedAdmin;
    private int votingRound;

    public ProposedAdminMessage(int votingRound, int proposedAdmin, int destination, TemperatureNode sender) {
        super(destination, sender);
        this.proposedAdmin = proposedAdmin;
        this.votingRound = votingRound;
    }

    public int getVotingRound() {
        return this.votingRound;
    }

    public int getPayload() {
        return this.proposedAdmin;
    }


    @Override
    public String toString() {
        return "Round: " + this.getVotingRound() + " ProposedAdmin: " + this.getPayload() + super.toString();

    }

}
