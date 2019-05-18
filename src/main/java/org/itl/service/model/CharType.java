package org.itl.service.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CharType {
    // LATIN
    A("U+0041", "A", Group.Latin), a("U+0061", "a", Group.Latin),
    B("U+0042", "B", Group.Latin), b("U+0062", "b", Group.Latin),
    C("U+0043", "C", Group.Latin), c("U+0063", "c", Group.Latin),
    D("U+0044", "D", Group.Latin), d("U+0064", "d", Group.Latin),
    E("U+0045", "E", Group.Latin), e("U+0065", "e", Group.Latin),
    F("U+0046", "F", Group.Latin), f("U+0066", "f", Group.Latin),
    G("U+0047", "G", Group.Latin), g("U+0067", "g", Group.Latin),
    H("U+0048", "H", Group.Latin), h("U+0068", "h", Group.Latin),
    I("U+0049", "I", Group.Latin), i("U+0069", "i", Group.Latin),
    J("U+004a", "J", Group.Latin), j("U+006a", "j", Group.Latin),
    K("U+004b", "K", Group.Latin), k("U+006b", "k", Group.Latin),
    L("U+004c", "L", Group.Latin), l("U+006c", "l", Group.Latin),
    M("U+004d", "M", Group.Latin), m("U+006d", "m", Group.Latin),
    N("U+004e", "N", Group.Latin), n("U+006e", "n", Group.Latin),
    O("U+004f", "O", Group.Latin), o("U+006f", "o", Group.Latin),
    P("U+0050", "P", Group.Latin), p("U+0070", "p", Group.Latin),
    Q("U+0051", "Q", Group.Latin), q("U+0071", "q", Group.Latin),
    R("U+0052", "R", Group.Latin), r("U+0072", "r", Group.Latin),
    S("U+0053", "S", Group.Latin), s("U+0073", "s", Group.Latin),
    T("U+0054", "T", Group.Latin), t("U+0074", "t", Group.Latin),
    U("U+0055", "U", Group.Latin), u("U+0075", "u", Group.Latin),
    V("U+0056", "V", Group.Latin), v("U+0076", "v", Group.Latin),
    W("U+0057", "W", Group.Latin), w("U+0077", "w", Group.Latin),
    X("U+0058", "X", Group.Latin), x("U+0078", "x", Group.Latin),
    Y("U+0059", "Y", Group.Latin), y("U+0079", "y", Group.Latin),
    Z("U+005a", "Z", Group.Latin), z("U+007a", "z", Group.Latin),
    // GREEK
    Omega("U+005a", "Omega", Group.Greek), omega("U+005a", "omega", Group.Greek),
    Pi("U+005a", "Pi", Group.Greek), pi("U+005a", "pi", Group.Greek),

    // DIGITS
    One("U+0031", "1", Group.Digit), Two("U+0032", "2", Group.Digit),
    Three("U+0033", "3", Group.Digit), Four("U+0034", "4", Group.Digit),
    Five("U+0035", "5", Group.Digit), Six("U+0036", "6", Group.Digit),
    Seven("U+0037", "7", Group.Digit), Eight("U+0038", "8", Group.Digit),
    Nine("U+0039", "9", Group.Digit), Zero("U+0030", "0", Group.Digit),

    LeftRoundBracket("U+0028", "(", Group.Parenthesis), RightRoundBracket("U+0029", ")", Group.Parenthesis),
    LeftCurlyBracket("U+007B", "{", Group.Parenthesis), RightCurlyBracket("U+007D", "}", Group.Parenthesis),
    LeftSquareBracket("U+005B", "[", Group.Parenthesis), RightSquareBracket("U+005D", "]", Group.Parenthesis),

    EqualsSign("U+003D", "=", Group.MathematicalSymbol),
    PlusSign("U+002B", "+", Group.MathematicalSymbol),
    FractionSlash("U+2044", "/", Group.MathematicalSymbol),
    HyphenMinus("U+2212", "-", Group.MathematicalSymbol),
    Asterisk("U+2217", "*", Group.MathematicalSymbol), // this may indicate a fraction
    FullStop("U+002E", ".", Group.Other), // this may indicate also a bullet or a multiplication

    RootSymbol("U+221A", "sqrt", Group.MathematicalSymbol),
    IntegralSymbol("U+222B", "integral", Group.MathematicalSymbol),
    infinitySymbol("U+221E", "infinity", Group.MathematicalSymbol),

    Undefined("U+", "undefined", Group.Other);
    /**
     * This id is the international recognized Unicode identifier.
     */
    private String unicodeId;
    /**
     * This string represents how this will be displayed using only ASCII.
     */
    private String name;
    /**
     * Indicates to what group it belongs.
     */
    private Group group;

    private static Map<String, CharType> namesMap = Stream.of(CharType.values()).collect(Collectors.toMap(ct->ct.name, ct->ct));

    private static EnumSet<CharType> lowerCaseLatin = EnumSet.of(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z);

    CharType(String unicodeId, String name, Group group) {
        this.unicodeId = unicodeId;
        this.name = name;
        this.group = group;
    }

    public static CharType forName(String name) {
        return namesMap.getOrDefault(name, Undefined);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public Group getGroup() {
        return group;
    }

    public boolean isLatinLoweCase() {
        return lowerCaseLatin.contains(this);
    }
}
