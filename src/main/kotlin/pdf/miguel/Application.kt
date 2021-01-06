package pdf.miguel

import com.itextpdf.io.font.FontConstants
import com.itextpdf.kernel.colors.ColorConstants
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.geom.Rectangle
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfPage
import com.itextpdf.kernel.pdf.PdfReader
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.canvas.PdfCanvas
import com.itextpdf.layout.Document
import com.itextpdf.pdfcleanup.PdfCleanUpLocation
import com.itextpdf.pdfcleanup.PdfCleanUpTool
import java.io.File
import java.util.*



fun main(args: Array<String>) {
    val pdfFolder = args[0]
    val pdfFolderDest = args[1]

    File(pdfFolder).walk().forEach { pdfFile ->
        if(pdfFile.isFile){
            modifyPDFCordinates(
                pdfFile.absolutePath,
                pdfFolderDest
            )

        }
    }
}

fun modifyPDFCordinates(src : String, dest : String) {

    val directory = File(dest)
    if (!directory.exists()) {
        directory.mkdir()

    }


    val pdfDoc = PdfDocument(PdfReader(src), PdfWriter(dest + "\\" + File(src).name ))
    val canvas: PdfCanvas
    val rectangle =  Rectangle(543F, 512F, 10F, 10F)
    val cleanUpLocations: MutableList<PdfCleanUpLocation> = ArrayList()
    cleanUpLocations.add(PdfCleanUpLocation(1, rectangle,  ColorConstants.WHITE))

    val cleaner = PdfCleanUpTool(pdfDoc, cleanUpLocations)
    cleaner.cleanUp()


    val page: PdfPage = pdfDoc.firstPage
    val pageSize = page.pageSize
    canvas = PdfCanvas(page)
    // add new content
    canvas.beginText().setFontAndSize(
        PdfFontFactory.createFont(FontConstants.HELVETICA), 10F
    )
        .moveText(541.0,511.8)
        .showText("0")
        .endText();


    pdfDoc.close()
}





