package com.modds.redisjava;

public class Sds implements ISds {
    @Override
    public Sdshdr sdsNewLen(char[] init, int initLen) {
        Sdshdr sh;
        if (init != null) {
            sh = new Sdshdr(initLen, 0, init);
        } else {
            sh = new Sdshdr();
        }

        sh.len = initLen;
        sh.free = 0;
        return sh;
    }

    @Override
    public Sdshdr sdsNew(char[] init) {
        return null;
    }

    @Override
    public Sdshdr sdsEmpty() {
        return null;
    }

    @Override
    public int sdsLen() {
        return 0;
    }

    @Override
    public Sdshdr sdsDup() {
        return null;
    }

    @Override
    public void sdsFree() {

    }

    @Override
    public Sdshdr sdsGrowZero(Sdshdr s, int len) {
        return null;
    }

    @Override
    public Sdshdr sdsCatLen(Sdshdr s, char[] chars, int len) {
        return null;
    }

    @Override
    public Sdshdr sdsCat(Sdshdr s, char[] chars) {
        return null;
    }

    @Override
    public Sdshdr sdsCatSds(Sdshdr s, Sdshdr t) {
        return null;
    }

    @Override
    public Sdshdr sdsCpyLen(Sdshdr s, Sdshdr t, int len) {
        return null;
    }

    @Override
    public Sdshdr sdsCpy(Sdshdr s, Sdshdr t) {
        return null;
    }
}
