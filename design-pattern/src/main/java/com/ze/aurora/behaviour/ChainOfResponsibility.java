package com.ze.aurora.behaviour;

/**
 * 责任链
 *
 * 申请聚餐费用时，可能会经过“项目经理 - 部门经理 - 总经理”这样的审批顺序
 * @author Aurora
 * @date 2021/12/17 14:14
 */
public class ChainOfResponsibility {
    abstract class Handler {
        protected Handler successor = null;
        abstract void handle(int count);
    }

    class ProjectManagerHandler extends Handler {
        boolean someCondition = true;

        public void setSuccessor(Handler successor) {
            this.successor = successor;
        }

        @Override
        void handle(int count) {
            if (count < 500) {
                if (someCondition) {
                    System.out.println("项目经理审批通过");
                } else {
                    System.out.println("项目经理拒绝审批");
                }
            } else {
                System.out.println("项目经理 -> 部门经理");
                successor.handle(count);
            }
        }
    }

    class DepartmentManagerHandler extends Handler {
        boolean someCondition = true;

        public void setSuccessor(Handler successor) {
            this.successor = successor;
        }

        @Override
        void handle(int count) {
            if (count < 1000) {
                if (someCondition) {
                    System.out.println("部门经理审批通过");
                } else {
                    System.out.println("部门经理拒绝审批");
                }
            } else {
                System.out.println("部门经理 -> 总经理");
                successor.handle(count);
            }
        }
    }

    class GeneralManagerHandler extends Handler {
        boolean someCondition = true;

        public void setSuccessor(Handler successor) {
            this.successor = successor;
        }

        @Override
        void handle(int count) {
            if (count < 2000 && someCondition) {
                    System.out.println("总经理审批通过");
            } else {
                System.out.println("总经理拒绝审批");
            }
        }
    }

    public void test() {
        /** 构造责任链 */
        ProjectManagerHandler projectManagerHandler = new ProjectManagerHandler();
        DepartmentManagerHandler departmentManagerHandler = new DepartmentManagerHandler();
        GeneralManagerHandler generalManagerHandler = new GeneralManagerHandler();

        projectManagerHandler.setSuccessor(departmentManagerHandler);
        departmentManagerHandler.setSuccessor(generalManagerHandler);

        /** 费用申请 */
        projectManagerHandler.handle(1200);
    }

    public static void main(String[] args) {
        ChainOfResponsibility chain = new ChainOfResponsibility();
        chain.test();
    }
}
