package com.ze.aurora.behaviour;

/**
 * 命令模式
 * <p>
 * 存在一个遥控器，可装配多个可拆卸的控件，每个控件都只有开和关两个操作
 * 例如控制灯光开关、控制音响开关等等。
 *
 * @author Aurora
 * @date 2021/11/14 20:21
 */
public class Command {

    /**
     * 封装的命令
     */
    interface ICommand {
        void execute();
        void undo();
    }

    class Light {
        void on() {
            System.out.println("light is on");
        }

        void off() {
            System.out.println("light is off");
        }
    }

    class LightOnCommand implements ICommand {
        Light light;

        LightOnCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.on();
        }

        @Override
        public void undo() {
            light.off();
        }
    }

    class LightOffCommand implements ICommand {
        Light light;

        LightOffCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.off();
        }

        @Override
        public void undo() {
            light.on();
        }
    }

    class NoCommand implements ICommand {

        @Override
        public void execute() {
            System.out.println("No Command");
        }

        @Override
        public void undo() {
            System.out.println("No Command");
        }
    }

    /**
     * 遥控器类，可以请求执行当前装配的控件的开关操作
     */
    class RemoteControl {
        ICommand[] onCommands;
        ICommand[] offCommands;
        ICommand undoCommand;

        RemoteControl(int size) {
            if (size <= 0) throw new IllegalArgumentException();
            onCommands = new ICommand[size];
            offCommands = new ICommand[size];
            undoCommand = null;

            // 令数组初始化为空命令
            for (int i = 0; i < size; i ++) {
                onCommands[i] = new NoCommand();
                offCommands[i] = new NoCommand();
            }
        }

        void setOnCommands(ICommand command, int position) {
            if (position < 0 || position > onCommands.length - 1) throw new IllegalArgumentException();
            onCommands[position] = command;
        }

        void setOffCommands(ICommand command, int position) {
            if (position < 0 || position > offCommands.length - 1) throw new IllegalArgumentException();
            offCommands[position] = command;
        }

        void onCommand(int position) {
            if (position < 0 || position > onCommands.length - 1) throw new IllegalArgumentException();
            onCommands[position].execute();
            undoCommand = onCommands[position];
        }

        void offCommand(int position) {
            if (position < 0 || position > offCommands.length - 1) throw new IllegalArgumentException();
            offCommands[position].execute();
            undoCommand = offCommands[position];
        }

        void undo() {
            undoCommand.undo();
        }
    }

    /**
     * 测试
     */
    public void test() {
        RemoteControl control = new RemoteControl(1);
        Light light = new Light();
        control.setOnCommands(new LightOnCommand(light), 0);
        control.setOffCommands(new LightOffCommand(light), 0);

        control.onCommand(0);
        control.offCommand(0);
        control.undo();
    }
}
