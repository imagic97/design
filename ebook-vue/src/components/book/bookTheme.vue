<template>
  <div class="themeControl" v-show="menuVisible && menuShow === 2">
    <div>
      <el-row>
        <el-col :span="12">
          <div
            class="theme theme-dark"
            @click="setCurrentTheme('dark')"
            :class="{'currentTheme':isCurrentTheme('dark')}"
          ></div>
        </el-col>
        <el-col :span="12">
          <div
            class="theme theme-light"
            @click="setCurrentTheme('light')"
            :class="{'currentTheme':isCurrentTheme('light')}"
          ></div>
        </el-col>
      </el-row>
    </div>
    <div class="fontSize">
      <input class="fontSizeValue" v-model="zitiSize" type="range" max="34" min="10" step="6" />
    </div>
  </div>
</template>

<script>
import { ebookMixin } from "@/util/mixin";
//import { addCss, removeCss } from "@/util/utils";
export default {
  mixins: [ebookMixin],
  data() {
    return {
      currentTheme: "light"
    };
  },

  computed: {
    zitiSize: {
      get() {
        return this.fontSize;
      },
      set(value) {
        this.setFontSize(value);
      }
    }
  },
  methods: {
    setCurrentTheme(value) {
      if (value === this.currentTheme) return;
      let removeDom = document.getElementById("themeStyle");
      if (removeDom != null) {
        removeDom.parentNode.removeChild(removeDom);
      }
      if (value === "dark") {
        this.CreateStyle(this.THEME_DARK, "themeStyle");
      } else {
        this.CreateStyle(this.THEME_LIGHT, "themeStyle");
      }
      this.currentTheme = value;
    },
    isCurrentTheme(value) {
      return this.currentTheme === value;
    }
  }
};
</script>

<style scoped>
.themeControl {
  position: absolute;
  bottom: 64px;
  width: 100%;
  border-radius: 16px 16px 0 0;
  box-shadow: 0 -8px 21px rgba(0, 25, 104, 0.3);
  padding: 18px 0 54px 0;
}

.fontSize {
  margin: 0 auto;
  padding: 16px 0 0 0;
  width: 86%;
}
.theme {
  margin: 16px 60px;
  height: 30px;
  border-radius: 15px;
}

@media screen and (max-width: 500px) {
  .theme {
    margin: 16px 23px;
    height: 30px;
    border-radius: 15px;
  }
}
.currentTheme {
  border: 2px solid #1b88ee;
}

.theme-dark {
  background: rgb(56, 56, 56);
}
.theme-light {
  background: #f1f1f1;
}

.fontSizeValue {
  width: 100%;
  -webkit-appearance: none;
  height: 8px;
  background: -webkit-linear-gradient(#1b88ee, #1b88ee) no-repeat, #1b88ee;
  background-size: 100%;
  border-radius: 4px;
}

.fontSizeValue:focus {
  outline: none;
}
.fontSizeValue::-webkit-slider-thumb {
  -webkit-appearance: none;
  height: 24px;
  width: 24px;
  border-radius: 50%;
  background: #bbbbe0;
  border: none;
}
</style>
