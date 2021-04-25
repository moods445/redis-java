package com.modds.redisjava;

public interface ISds {
    static int sdsLen(final Sdshdr sdshdr) {
        return sdshdr.getLen();
    }

    static int sdsAvail(final Sdshdr s) {
        return s.getFree();
    }

    Sdshdr sdsNewLen(final char[] init, int len);

    Sdshdr sdsNew(final char[] init);

    Sdshdr sdsEmpty();

    int sdsLen();

    /**
     * 复制一份
     *
     * @return
     */
    Sdshdr sdsDup();

    /**
     * 释放
     */
    void sdsFree();

    Sdshdr sdsGrowZero(Sdshdr s, int len);

    /**
     * append
     *
     * @param s
     * @return
     */
    Sdshdr sdsCatLen(Sdshdr s, char[] chars, int len);

    Sdshdr sdsCat(Sdshdr s, char[] chars);

    Sdshdr sdsCatSds(Sdshdr s, final Sdshdr t);

    Sdshdr sdsCpyLen(Sdshdr s, final Sdshdr t, int len);

    Sdshdr sdsCpy(Sdshdr s, final Sdshdr t);
}
