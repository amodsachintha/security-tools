package org.owasp.dependencycheck.xml;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.ThreadSafe;

/**
 * This is a utility class to convert named XML Entities (such as &oslash;) into
 * its HTML encoded Unicode code point (i.e. &amp;#248;). This is a slightly
 * modified (class/method rename) from an SO answer:
 * https://stackoverflow.com/questions/7286428/help-the-java-sax-parser-to-understand-bad-xml
 *
 * @author https://stackoverflow.com/users/823393/oldcurmudgeon
 */
@ThreadSafe
public final class XmlEntity {

    /**
     * The map of HTML entities.
     */
    private static final Map<String, Integer> SPECIALS;

    //<editor-fold defaultstate="collapsed" desc="Initialize SPECIALS">
    /*
     * Create a map HTML Named Entities to their numeric equivalent. Derived
     * from Wikipedia
     * http://en.wikipedia.org/wiki/List_of_XML_and_HTML_character_entity_references
     */
    static {
        final Map<String, Integer> map = new HashMap<>();
        map.put("quot", 34);
        map.put("amp", 38);
        map.put("apos", 39);
        map.put("lt", 60);
        map.put("gt", 62);
        map.put("nbsp", 160);
        map.put("iexcl", 161);
        map.put("cent", 162);
        map.put("pound", 163);
        map.put("curren", 164);
        map.put("yen", 165);
        map.put("brvbar", 166);
        map.put("sect", 167);
        map.put("uml", 168);
        map.put("copy", 169);
        map.put("ordf", 170);
        map.put("laquo", 171);
        map.put("not", 172);
        map.put("shy", 173);
        map.put("reg", 174);
        map.put("macr", 175);
        map.put("deg", 176);
        map.put("plusmn", 177);
        map.put("sup2", 178);
        map.put("sup3", 179);
        map.put("acute", 180);
        map.put("micro", 181);
        map.put("para", 182);
        map.put("middot", 183);
        map.put("cedil", 184);
        map.put("sup1", 185);
        map.put("ordm", 186);
        map.put("raquo", 187);
        map.put("frac14", 188);
        map.put("frac12", 189);
        map.put("frac34", 190);
        map.put("iquest", 191);
        map.put("Agrave", 192);
        map.put("Aacute", 193);
        map.put("Acirc", 194);
        map.put("Atilde", 195);
        map.put("Auml", 196);
        map.put("Aring", 197);
        map.put("AElig", 198);
        map.put("Ccedil", 199);
        map.put("Egrave", 200);
        map.put("Eacute", 201);
        map.put("Ecirc", 202);
        map.put("Euml", 203);
        map.put("Igrave", 204);
        map.put("Iacute", 205);
        map.put("Icirc", 206);
        map.put("Iuml", 207);
        map.put("ETH", 208);
        map.put("Ntilde", 209);
        map.put("Ograve", 210);
        map.put("Oacute", 211);
        map.put("Ocirc", 212);
        map.put("Otilde", 213);
        map.put("Ouml", 214);
        map.put("times", 215);
        map.put("Oslash", 216);
        map.put("Ugrave", 217);
        map.put("Uacute", 218);
        map.put("Ucirc", 219);
        map.put("Uuml", 220);
        map.put("Yacute", 221);
        map.put("THORN", 222);
        map.put("szlig", 223);
        map.put("agrave", 224);
        map.put("aacute", 225);
        map.put("acirc", 226);
        map.put("atilde", 227);
        map.put("auml", 228);
        map.put("aring", 229);
        map.put("aelig", 230);
        map.put("ccedil", 231);
        map.put("egrave", 232);
        map.put("eacute", 233);
        map.put("ecirc", 234);
        map.put("euml", 235);
        map.put("igrave", 236);
        map.put("iacute", 237);
        map.put("icirc", 238);
        map.put("iuml", 239);
        map.put("eth", 240);
        map.put("ntilde", 241);
        map.put("ograve", 242);
        map.put("oacute", 243);
        map.put("ocirc", 244);
        map.put("otilde", 245);
        map.put("ouml", 246);
        map.put("divide", 247);
        map.put("oslash", 248);
        map.put("ugrave", 249);
        map.put("uacute", 250);
        map.put("ucirc", 251);
        map.put("uuml", 252);
        map.put("yacute", 253);
        map.put("thorn", 254);
        map.put("yuml", 255);
        map.put("OElig", 338);
        map.put("oelig", 339);
        map.put("Scaron", 352);
        map.put("scaron", 353);
        map.put("Yuml", 376);
        map.put("fnof", 402);
        map.put("circ", 710);
        map.put("tilde", 732);
        map.put("Alpha", 913);
        map.put("Beta", 914);
        map.put("Gamma", 915);
        map.put("Delta", 916);
        map.put("Epsilon", 917);
        map.put("Zeta", 918);
        map.put("Eta", 919);
        map.put("Theta", 920);
        map.put("Iota", 921);
        map.put("Kappa", 922);
        map.put("Lambda", 923);
        map.put("Mu", 924);
        map.put("Nu", 925);
        map.put("Xi", 926);
        map.put("Omicron", 927);
        map.put("Pi", 928);
        map.put("Rho", 929);
        map.put("Sigma", 931);
        map.put("Tau", 932);
        map.put("Upsilon", 933);
        map.put("Phi", 934);
        map.put("Chi", 935);
        map.put("Psi", 936);
        map.put("Omega", 937);
        map.put("alpha", 945);
        map.put("beta", 946);
        map.put("gamma", 947);
        map.put("delta", 948);
        map.put("epsilon", 949);
        map.put("zeta", 950);
        map.put("eta", 951);
        map.put("theta", 952);
        map.put("iota", 953);
        map.put("kappa", 954);
        map.put("lambda", 955);
        map.put("mu", 956);
        map.put("nu", 957);
        map.put("xi", 958);
        map.put("omicron", 959);
        map.put("pi", 960);
        map.put("rho", 961);
        map.put("sigmaf", 962);
        map.put("sigma", 963);
        map.put("tau", 964);
        map.put("upsilon", 965);
        map.put("phi", 966);
        map.put("chi", 967);
        map.put("psi", 968);
        map.put("omega", 969);
        map.put("thetasym", 977);
        map.put("upsih", 978);
        map.put("piv", 982);
        map.put("ensp", 8194);
        map.put("emsp", 8195);
        map.put("thinsp", 8201);
        map.put("zwnj", 8204);
        map.put("zwj", 8205);
        map.put("lrm", 8206);
        map.put("rlm", 8207);
        map.put("ndash", 8211);
        map.put("mdash", 8212);
        map.put("lsquo", 8216);
        map.put("rsquo", 8217);
        map.put("sbquo", 8218);
        map.put("ldquo", 8220);
        map.put("rdquo", 8221);
        map.put("bdquo", 8222);
        map.put("dagger", 8224);
        map.put("Dagger", 8225);
        map.put("bull", 8226);
        map.put("hellip", 8230);
        map.put("permil", 8240);
        map.put("prime", 8242);
        map.put("Prime", 8243);
        map.put("lsaquo", 8249);
        map.put("rsaquo", 8250);
        map.put("oline", 8254);
        map.put("frasl", 8260);
        map.put("euro", 8364);
        map.put("image", 8465);
        map.put("weierp", 8472);
        map.put("real", 8476);
        map.put("trade", 8482);
        map.put("alefsym", 8501);
        map.put("larr", 8592);
        map.put("uarr", 8593);
        map.put("rarr", 8594);
        map.put("darr", 8595);
        map.put("harr", 8596);
        map.put("crarr", 8629);
        map.put("lArr", 8656);
        map.put("uArr", 8657);
        map.put("rArr", 8658);
        map.put("dArr", 8659);
        map.put("hArr", 8660);
        map.put("forall", 8704);
        map.put("part", 8706);
        map.put("exist", 8707);
        map.put("empty", 8709);
        map.put("nabla", 8711);
        map.put("isin", 8712);
        map.put("notin", 8713);
        map.put("ni", 8715);
        map.put("prod", 8719);
        map.put("sum", 8721);
        map.put("minus", 8722);
        map.put("lowast", 8727);
        map.put("radic", 8730);
        map.put("prop", 8733);
        map.put("infin", 8734);
        map.put("ang", 8736);
        map.put("and", 8743);
        map.put("or", 8744);
        map.put("cap", 8745);
        map.put("cup", 8746);
        map.put("int", 8747);
        map.put("there4", 8756);
        map.put("sim", 8764);
        map.put("cong", 8773);
        map.put("asymp", 8776);
        map.put("ne", 8800);
        map.put("equiv", 8801);
        map.put("le", 8804);
        map.put("ge", 8805);
        map.put("sub", 8834);
        map.put("sup", 8835);
        map.put("nsub", 8836);
        map.put("sube", 8838);
        map.put("supe", 8839);
        map.put("oplus", 8853);
        map.put("otimes", 8855);
        map.put("perp", 8869);
        map.put("sdot", 8901);
        map.put("lceil", 8968);
        map.put("rceil", 8969);
        map.put("lfloor", 8970);
        map.put("rfloor", 8971);
        map.put("lang", 10216);
        map.put("rang", 10217);
        map.put("loz", 9674);
        map.put("spades", 9824);
        map.put("clubs", 9827);
        map.put("hearts", 9829);
        map.put("diams", 9830);
        SPECIALS = Collections.unmodifiableMap(map);
    }
    //</editor-fold>

    /**
     * Private constructor for a utility class.
     */
    private XmlEntity() {
    }

    /**
     * Converts a named XML entity into its HTML encoded Unicode code point.
     *
     * @param s the named entity (note, this should not include the leading
     * '&amp;' or trailing ';'
     * @return the HTML encoded Unicode code point representation of the named
     * entity
     */
    public static String fromNamedReference(CharSequence s) {
        if (s == null) {
            return null;
        }
        final Integer code = SPECIALS.get(s.toString());
        if (code != null) {
            return "&#" + code + ";";
        }
        return null;
    }
}
