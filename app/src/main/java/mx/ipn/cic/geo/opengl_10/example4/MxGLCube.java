package mx.ipn.cic.geo.opengl_10.example4;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

// This class is an object representation of a Cube containing the vertex information,
// color information, the vertex indices and drawing functionality, which is called
// by the renderer.
public class MxGLCube {

    // The buffer holding the vertices
    private FloatBuffer vertexBuffer;
    // The buffer holding the color values
    private FloatBuffer colorBuffer;
    // The buffer holding the indices
    private ByteBuffer  indexBuffer;

    // The initial vertex definition. It defines the eight vertices a cube has
    // based on the OpenGL coordinate system
    private float vertices[] = {
            -1.0f, -1.0f, -1.0f,                // Lower back left (0)
             1.0f, -1.0f, -1.0f,                // Lower back right (1)
             1.0f,  1.0f, -1.0f,                // Upper back right (2)
            -1.0f,  1.0f, -1.0f,                // Upper back left (3)
            -1.0f, -1.0f,  1.0f,                // Lower front left (4)
             1.0f, -1.0f,  1.0f,                // Lower front right (5)
             1.0f,  1.0f,  1.0f,                // Upper front right (6)
            -1.0f,  1.0f,  1.0f                 // Upper front left (7)
    };

    // The initial color definition
    private float colors[] = {
            0.0f,  1.0f,  0.0f,  1.0f,
            0.0f,  1.0f,  0.0f,  1.0f,
            1.0f,  0.5f,  0.0f,  1.0f,
            1.0f,  0.5f,  0.0f,  1.0f,
            1.0f,  0.0f,  0.0f,  1.0f,
            1.0f,  0.0f,  0.0f,  1.0f,
            0.0f,  0.0f,  1.0f,  1.0f,
            1.0f,  0.0f,  1.0f,  1.0f
    };

    // The initial indices definition. The indices define our triangles.
    // Always two define one of the six faces a cube has.
    private byte indices[] = {
            // Example:
            // Face made of the vertices lower back left (lbl),
            // lfl, lfr, lbl, lfr, lbr
            0, 4, 5, 0, 5, 1,
            // And so on...
            1, 5, 6, 1, 6, 2,
            2, 6, 7, 2, 7, 3,
            3, 7, 4, 3, 4, 0,
            4, 7, 6, 4, 6, 5,
            3, 0, 1, 3, 1, 2
    };

    // The Cube constructor. Initiate the buffers.
    public MxGLCube() {
        //
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(this.vertices.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        this.vertexBuffer = byteBuf.asFloatBuffer();
        this.vertexBuffer.put(vertices);
        this.vertexBuffer.position(0);

        byteBuf = ByteBuffer.allocateDirect(this.colors.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        this.colorBuffer = byteBuf.asFloatBuffer();
        this.colorBuffer.put(colors);
        this.colorBuffer.position(0);

        this.indexBuffer = ByteBuffer.allocateDirect(this.indices.length);
        this.indexBuffer.put(this.indices);
        this.indexBuffer.position(0);
    }

    // The object own drawing function. Called from the renderer to redraw
    // this instance with possible changes in values.
    // @param gl - The GL Context
    public void draw(GL10 gl) {
        // Set the face rotation
        gl.glFrontFace(GL10.GL_CW);

        // Point to our buffers
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, this.vertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, this.colorBuffer);

        // Enable the vertex and color state
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        // Draw the vertices as triangles, based on the Index Buffer information
        gl.glDrawElements(GL10.GL_TRIANGLES, 36, GL10.GL_UNSIGNED_BYTE, indexBuffer);

        // Disable the client state before leaving
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}
