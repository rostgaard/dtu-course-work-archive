/*
 * A message containing a proposal for a new admin node.
 */
package networktools;

import temperaturemonitoring.TemperatureNode;

/**
 *
 * @author Kim Rostgaard Christensen
 */
public class ProposedAdminMessage extends Message {

    private int proposedAdmin;
    private int votingRound;

    /**
     * Constructor.
     *
     * @param votingRound The voting round.
     * @param proposedAdmin The new proposed admin.
     * @param destination The destination ID.
     * @param sender The sender ID.
     */
    public ProposedAdminMessage(int votingRound, int proposedAdmin, int destination, TemperatureNode sender) {
        super(destination, sender);
        this.proposedAdmin = proposedAdmin;
        this.votingRound = votingRound;
    }

    /**
     * Getter.
     *
     * @return The voting round embedded in the message.
     */
    public int getVotingRound() {
        return this.votingRound;
    }
    /**
     * Getter.
     *
     * @return The proposed admin ID embedded in the message.
     */
    public int getPayload() {
        return this.proposedAdmin;
    }

    /**
     * Stringify the object.
     *
     * @return The object in a debug-friendly format.
     */
    @Override
    public String toString() {
        return "Round: " + this.getVotingRound() + " ProposedAdmin: " + this.getPayload() + super.toString();

    }

}
