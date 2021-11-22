package com.ze.aurora.behaviour;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aurora
 * @date 2021/12/17 15:16
 */
public class State {
    abstract class VoteState {
        abstract void vote(String user, String content, VoteSystem system);
    }

    class NormalVoteState extends VoteState {
        @Override
        void vote(String user, String content, VoteSystem system) {
            System.out.println("投票成功");
        }
    }

    class RepeatVoteState extends VoteState {

        @Override
        void vote(String user, String content, VoteSystem system) {
            System.out.println("请不要重复投票");
        }
    }

    class MaliciousVoteState extends VoteState {

        @Override
        void vote(String user, String content, VoteSystem system) {
            System.out.println("恶意投票，令投票作废");
            system.voteRecord.remove(user);
        }
    }

    class BlackVoteState extends VoteState {

        @Override
        void vote(String user, String content, VoteSystem system) {
            System.out.println("判定为极端恶意行为，永久封禁");
        }
    }

    class VoteSystem {
        VoteState state;
        Map<String, String> voteRecord;
        Map<String, Integer> voteCount;

        VoteSystem() {
            state = null;
            voteRecord = new HashMap<>();
            voteCount = new HashMap<>();
        }

        void vote(String user, String content) {
            voteRecord.put(user, content);
            int oldVoteCnt = voteCount.getOrDefault(user, 0);

            int newVoteCnt = oldVoteCnt + 1;
            voteCount.put(user, newVoteCnt);

            if (newVoteCnt == 1) {
                state = new NormalVoteState();
            } else if (newVoteCnt < 5) {
                state = new RepeatVoteState();
            } else if (newVoteCnt < 8) {
                state = new MaliciousVoteState();
            } else {
                state = new BlackVoteState();
            }
            state.vote(user, content, this);
        }
    }

    public void test() {
        VoteSystem system = new VoteSystem();
        for (int i = 0; i < 10; i++) {
            system.vote("aurora", "zhangsan");
        }
    }

    public static void main(String[] args) {
        State state = new State();
        state.test();
    }
}
