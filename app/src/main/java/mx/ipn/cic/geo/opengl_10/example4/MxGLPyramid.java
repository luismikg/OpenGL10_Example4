package mx.ipn.cic.geo.opengl_10.example4;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class MxGLPyramid {

    // The buffer holding the vertices
    private FloatBuffer vertexBuffer;
    // The buffer holding the color values
    private FloatBuffer colorBuffer;

    // The initial vertex definition
    private float vertices[] = { 
             0.0f,  1.0f,  0.0f,        // Top of triangle (Front)
            -1.0f, -1.0f,  1.0f,        // Left of triangle (Front)
             1.0f, -1.0f,  1.0f,        // Right of triangle (Front)
             0.0f,  1.0f,  0.0f,        // Top of triangle (Right)
             1.0f, -1.0f,  1.0f,        // Left of triangle (Right)
             1.0f, -1.0f, -1.0f,        // Right of triangle (Right)
             0.0f,  1.0f, 0.0f,         // Top of triangle (Back)
             1.0f, -1.0f, -1.0f,        // Left of triangle (Back)
            -1.0f, -1.0f, -1.0f,        // Right of triangle (Back)
             0.0f,  1.0f, 0.0f,         // Top of triangle (Left)
            -1.0f, -1.0f, -1.0f,        // Left of triangle (Left)
            -1.0f, -1.0f, 1.0f          // Right of triangle (Left)
    };

    // The initial color definition
    private float colors[] = {
            1.0f, 0.0f, 0.0f, 1.0f,         // Red
            0.0f, 1.0f, 0.0f, 1.0f,         // Green
            0.0f, 0.0f, 1.0f, 1.0f,         // Blue
            1.0f, 0.0f, 0.0f, 1.0f,         // Red
            0.0f, 0.0f, 1.0f, 1.0f,         // Blue
            0.0f, 1.0f, 0.0f, 1.0f,         // Green
            1.0f, 0.0f, 0.0f, 1.0f,         // Red
            0.0f, 1.0f, 0.0f, 1.0f,         // Green
            0.0f, 0.0f, 1.0f, 1.0f,         // Blue
            1.0f, 0.0f, 0.0f, 1.0f,         // Red
            0.0f, 0.0f, 1.0f, 1.0f,         // Blue
            0.0f, 1.0f, 0.0f, 1.0f          // Green
    };
    
    // The Pyramid constructor. Initiate the buffers.
    public MxGLPyramid() {

        ByteBuffer byteBuf = ByteBuffer.allocateDirect(this.vertices.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        this.vertexBuffer = byteBuf.asFloatBuffer();
        this.vertexBuffer.put(this.vertices);
        this.vertexBuffer.position(0);

        byteBuf = ByteBuffer.allocateDirect(this.colors.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        this.colorBuffer = byteBuf.asFloatBuffer();
        this.colorBuffer.put(this.colors);
        this.colorBuffer.position(0);
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

        // Draw the vertices as triangles
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, vertices.length / 3);

        // Disable the client state before leaving
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
}
