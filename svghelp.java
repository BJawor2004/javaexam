public class SvgHelperExample {

    // Funkcja generująca nagłówek SVG
    public static String svgHeader(int width, int height) {
        return String.format("""
                <svg xmlns="http://www.w3.org/2000/svg" width="%d" height="%d">
                """, width, height);
    }

    // Funkcja generująca koniec SVG
    public static String svgFooter() {
        return "</svg>";
    }

    // Funkcja do rysowania koła
    public static String drawCircle(int cx, int cy, int r, String color) {
        return String.format("<circle cx=\"%d\" cy=\"%d\" r=\"%d\" fill=\"%s\" />%n", cx, cy, r, color);
    }

    // Funkcja do rysowania prostokąta
    public static String drawRect(int x, int y, int width, int height, String color) {
        return String.format("<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" fill=\"%s\" />%n",
                x, y, width, height, color);
    }

    // Funkcja do rysowania linii
    public static String drawLine(int x1, int y1, int x2, int y2, String color, int strokeWidth) {
        return String.format("<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke=\"%s\" stroke-width=\"%d\" />%n",
                x1, y1, x2, y2, color, strokeWidth);
    }

    // Funkcja do rysowania elipsy
    public static String drawEllipse(int cx, int cy, int rx, int ry, String color) {
        return String.format("<ellipse cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\" fill=\"%s\" />%n",
                cx, cy, rx, ry, color);
    }
}
