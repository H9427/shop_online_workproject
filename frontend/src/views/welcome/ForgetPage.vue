<template>
  <div>
    <div style="margin: 30px 20px">
      <el-steps :active="active" finish-status="success" align-center>
        <el-step title="验证手机号" />
        <el-step title="重新设定密码" />
      </el-steps>
    </div>
    <transition name="el-fade-in-linear" mode="out-in">
      <div style="text-align: center; margin: 0 20px; height: 100%" v-if="active === 0">
        <div style="margin-top: 80px">
          <div style="font-size: 25px; font-weight: bold; margin-bottom: 10px">重置密码</div>
          <div style="font-size: 14px; color: grey">请输入需要重置密码用户名和手机号</div>
        </div>
        <div style="margin-top: 50px">
          <el-form :model="form" :rules="rules" @validate="onValidate" ref="formRef">
            <el-form-item prop="username">
              <el-input v-model="form.username" type="username" placeholder="请输入用户名">
                <template #prefix>
                  <el-icon><User /></el-icon>
                </template>
              </el-input>
            </el-form-item>
            <el-form-item prop="phoneNumber">
              <el-input v-model="form.phoneNumber" type="phoneNumber" placeholder="请输入手机号">
                <template #prefix>
                  <el-icon><Phone /></el-icon>
                </template>
              </el-input>
            </el-form-item>
          </el-form>
        </div>
        <div style="margin-top: 70px">
          <el-button @click="confirmReset()" style="width: 270px" type="danger" plain>开始重置密码</el-button>
          <el-divider>
            <span style="color: grey; font-size: 13px">想起密码了？</span>
          </el-divider>
          <el-button @click="$router.push('/login')" style="width: 270px;" type="success" plain>返回登录</el-button>
        </div>
      </div>
    </transition>
    <transition name="el-fade-in-linear" mode="out-in">
      <div style="text-align: center; margin: 0 20px; height: 100%" v-if="active === 1">
        <div style="margin-top: 80px">
          <div style="font-size: 25px; font-weight: bold">重置密码</div>
          <div style="font-size: 14px; color: grey">请填写您的新密码，务必牢记，防止丢失</div>
        </div>
        <div style="margin-top: 50px">
          <el-form :model="form" :rules="rules" @validate="onValidate" ref="formRef">
            <el-form-item prop="password">
              <el-input v-model="form.password" :maxlength="16" type="password" placeholder="新密码">
                <template #prefix>
                  <el-icon><Lock /></el-icon>
                </template>
              </el-input>
            </el-form-item>
            <el-form-item prop="password_repeat">
              <el-input v-model="form.password_repeat" :maxlength="16" type="password" placeholder="重复新密码">
                <template #prefix>
                  <el-icon><Lock /></el-icon>
                </template>
              </el-input>
            </el-form-item>
          </el-form>
        </div>
        <div style="margin-top: 70px">
          <el-button @click="doReset()" style="width: 270px" type="danger" plain>立即重置密码</el-button>
        </div>
      </div>
    </transition>
  </div>
</template>

<script setup>
import { reactive, ref } from "vue";
import { Lock, Phone, User } from "@element-plus/icons-vue";
import { get, post } from "@/net";
import { ElMessage } from "element-plus";
import router from "@/router";

const active = ref(0);

const form = reactive({
  email: "",
  code: "",
  password: "",
  password_repeat: "",
});

const validatePassword = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("请再次输入密码"));
  } else if (value !== form.password) {
    callback(new Error("两次输入的密码不一致"));
  } else {
    callback();
  }
};

const rules = {
  username: [
    { required: true, message: "请输入用户名", trigger: "blur" },
    { min: 2, max: 8, message: "用户名长度必须在2-8个字符之间", trigger: ["blur", "change"] },
  ],
  phoneNumber: [
    { required: true, message: "请输入手机号码", trigger: "blur" },
    { pattern: /^1\d{10}$/, message: "手机号必须是11位数字", trigger: ["blur", "change"] },
  ],
  password: [
    { required: true, message: "请输入密码", trigger: "blur" },
    { min: 6, max: 16, message: "密码的长度必须在6-16个字符之间", trigger: ["blur"] },
  ],
  password_repeat: [{ validator: validatePassword, trigger: ["blur", "change"] }],
};

const formRef = ref();
const isEmailValid = ref(false);
const coldTime = ref(0);

const onValidate = (prop, isValid) => {
  if (prop === "email") isEmailValid.value = isValid;
};

const confirmReset = () => {
  formRef.value.validate((isValid) => {
    if (isValid) {
      post(
        "/api/auth/reset-confirm",
        {
          email: form.email,
          code: form.code,
        },
        () => active.value++
      );
    }
  });
};

const doReset = () => {
  formRef.value.validate((isValid) => {
    if (isValid) {
      post(
        "/api/auth/reset-password",
        {
          email: form.email,
          code: form.code,
          password: form.password,
        },
        () => {
          ElMessage.success("密码重置成功，请重新登录");
          router.push("/");
        }
      );
    }
  });
};
</script>

<style scoped></style>
