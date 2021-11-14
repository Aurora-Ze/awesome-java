package com.ze.aurora.creation;

/**
 * 生成器
 *
 * Director 和 Builder，builder负责产品部件的制作，director进行指导组装整个产品
 * @author Aurora
 * @date 2021/11/11 15:04
 */
public class Builder {
    class Director {
        FileBuilder builder;

        Director(FileBuilder builder) {
            this.builder = builder;
        }

        void construct() {
            builder.buildHeader();
            builder.buildBody();
            builder.buildFooter();
        }
    }

    interface FileBuilder {
        void buildHeader();
        void buildBody();
        void buildFooter();
    }

    class TXTBuilder implements FileBuilder {

        @Override
        public void buildHeader() {
            System.out.println("TXT header");
        }

        @Override
        public void buildBody() {
            System.out.println("TXT body");
        }

        @Override
        public void buildFooter() {
            System.out.println("TXT footer");
        }
    }

    class XMLBuilder implements FileBuilder {

        @Override
        public void buildHeader() {
            System.out.println("XML header");
        }

        @Override
        public void buildBody() {
            System.out.println("XML body");
        }

        @Override
        public void buildFooter() {
            System.out.println("XML footer");
        }
    }

    public void test() {
        FileBuilder builder = new XMLBuilder();
        Director director = new Director(builder);
        director.construct();
    }
}
