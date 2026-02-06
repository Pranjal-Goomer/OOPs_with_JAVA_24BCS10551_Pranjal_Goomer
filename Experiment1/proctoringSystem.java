package Experiment1;

public class proctoringSystem {

    interface IdentityCheck {
        void verify();
    }

    interface BehaviourCheck {
        void monitor();
    }

    static class AIIdentityCheck implements IdentityCheck {
        public void verify() {
            System.out.println("AI identity verification");
        }
    }

    static class HumanBehaviourCheck implements BehaviourCheck {
        public void monitor() {
            System.out.println("Human behaviour monitoring");
        }
    }

    static class ProctoringController {
        IdentityCheck identity;
        BehaviourCheck behaviour;

        ProctoringController(IdentityCheck i, BehaviourCheck b) {
            identity = i;
            behaviour = b;
        }

        void startExam() {
            if (identity != null) identity.verify();
            if (behaviour != null) behaviour.monitor();
        }
    }

    public static void main(String[] args) {

        ProctoringController exam1 =
            new ProctoringController(
                new AIIdentityCheck(),
                null
            );
        exam1.startExam();

        System.out.println("-----");

        ProctoringController exam2 =
            new ProctoringController(
                new AIIdentityCheck(),
                new HumanBehaviourCheck()
            );
        exam2.startExam();
    }
}


