package org.lwjgl;

public abstract class PointerWrapperAbstract implements PointerWrapper {

    protected final long pointer;

    protected PointerWrapperAbstract(long pointer) {
        this.pointer = pointer;
    }

    public boolean isValid() {
        return pointer != 0;
    }

    public final void checkValid() {
        if (LWJGLUtil.DEBUG && !this.isValid())
            throw new IllegalStateException("This " + this.getClass().getSimpleName() + " pointer is not valid.");
    }

    public final long getPointer() {
        this.checkValid();
        return pointer;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PointerWrapperAbstract)) return false;

        PointerWrapperAbstract that = (PointerWrapperAbstract) o;

        return pointer == that.pointer;
    }

    public int hashCode() {
        return (int) (pointer ^ (pointer >>> 32));
    }

    public String toString() {
        return this.getClass().getSimpleName() + " pointer (0x" + Long.toHexString(pointer).toUpperCase() + ")";
    }
}
