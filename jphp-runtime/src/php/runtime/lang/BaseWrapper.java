package php.runtime.lang;

import php.runtime.env.Environment;
import php.runtime.lang.support.IComparableObject;
import php.runtime.memory.ArrayMemory;
import php.runtime.reflection.ClassEntity;

import java.lang.ref.WeakReference;

import static php.runtime.annotation.Reflection.BaseType;
import static php.runtime.annotation.Reflection.Ignore;

@Ignore
@BaseType
abstract public class BaseWrapper<T> implements IObject, IComparableObject<BaseWrapper> {
    protected final ArrayMemory __dynamicProperties__;
    protected ClassEntity __class__;
    protected final WeakReference<Environment> __env__;

    private boolean __isFinalized;

    protected T __wrappedObject;

    public BaseWrapper(Environment env, T wrappedObject){
        this(env, (ClassEntity) null);
        __class__ = env.fetchClass(getClass());
        __wrappedObject = wrappedObject;
    }

    public BaseWrapper(Environment env, ClassEntity clazz) {
        this.__class__ = clazz;
        this.__dynamicProperties__ = new ArrayMemory(true);
        this.__env__ = new WeakReference<Environment>(env);
    }

    public T getWrappedObject() {
        return __wrappedObject;
    }

    @Override
    final public int getPointer(){
        return super.hashCode();
    }

    @Override
    public ClassEntity getReflection() {
        return __class__;
    }

    @Override
    public ArrayMemory getProperties() {
        return __dynamicProperties__;
    }

    @Override
    public Environment getEnvironment() {
        return __env__.get();
    }

    @Override
    public boolean isFinalized() {
        return __isFinalized;
    }

    @Override
    public void doFinalize() {
        __isFinalized = true;
    }

    @Override
    public boolean isMock() {
        return __class__ == null;
    }

    @Override
    public void setAsMock() {
        __class__ = null;
    }

    @Override
    public boolean __equal(BaseWrapper iObject) {
        return iObject.getWrappedObject() == getWrappedObject();
    }

    @Override
    public boolean __identical(BaseWrapper iObject) {
        return iObject.getWrappedObject() == getWrappedObject();
    }

    @Override
    public boolean __greater(BaseWrapper iObject) {
        return false;
    }

    @Override
    public boolean __greaterEq(BaseWrapper iObject) {
        return false;
    }

    @Override
    public boolean __smaller(BaseWrapper iObject) {
        return false;
    }

    @Override
    public boolean __smallerEq(BaseWrapper iObject) {
        return false;
    }
}
