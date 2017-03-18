package com.example.aluno.projetores;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import com.example.aluno.projetores.fragments.EmprestimosFragment;
import com.example.aluno.projetores.fragments.HomeFragment;
import com.example.aluno.projetores.fragments.ProfessoresFragment;
import com.example.aluno.projetores.fragments.ProjetoresFragment;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Drawer navigationDrawerLeft;
    private AccountHeader headerNavigationLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instantiateViews();

        setToolbar();

        setNavigationDrawer(savedInstanceState);

    }

    private void instantiateViews(){
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
    }

    private void setNavigationDrawer(Bundle savedInstanceState) {
        //header of menu
        headerNavigationLeft = new AccountHeaderBuilder().withActivity(this).addProfiles(
                new ProfileDrawerItem().withEmail("Secretária").withIcon(R.drawable.ic_account_circle_white),
                new ProfileDrawerItem().withEmail("Professor").withIcon(R.drawable.ic_account_circle_white),
                new ProfileDrawerItem().withEmail("Técnico").withIcon(R.drawable.ic_account_circle_white)
        ).withHeaderBackground(R.color.colorPrimaryDark).build();

        PrimaryDrawerItem home = new PrimaryDrawerItem().withIdentifier(0).withName("Emprestar projetor").withIcon(R.drawable.ic_crop_free);
        PrimaryDrawerItem projetores = new PrimaryDrawerItem().withIdentifier(1).withName("Projetores").withIcon(R.drawable.ic_videocam);
        PrimaryDrawerItem professores = new PrimaryDrawerItem().withIdentifier(2).withName("Professores").withIcon(R.drawable.ic_account_box);
        PrimaryDrawerItem emprestimos = new PrimaryDrawerItem().withIdentifier(3).withName("Emprestimos").withIcon(R.drawable.ic_assignment);

        navigationDrawerLeft = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggleAnimated(true).withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(home,
                        new DividerDrawerItem(),
                        projetores,
                        professores,
                        emprestimos)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //TODO: Melhorar o jeito desse switch - Magic Numbers
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                        switch(position) {
                            case 1:
                                ft.replace(R.id.flMainContent, new HomeFragment());
                                mToolbar.setTitle("Leitor de QRCode");
                                ft.commit();
                                break;

                            case 3:
                                ft.replace(R.id.flMainContent, new ProjetoresFragment());
                                mToolbar.setTitle("Projetores");
                                ft.commit();
                                break;

                            case 4:
                                ft.replace(R.id.flMainContent, new ProfessoresFragment());
                                mToolbar.setTitle("Professores");
                                ft.commit();
                                break;

                            case 5:
                                ft.replace(R.id.flMainContent, new EmprestimosFragment());
                                mToolbar.setTitle("Emprestimos");
                                ft.commit();
                                break;
                        }
                        return false;
                    }
                }).withAccountHeader(headerNavigationLeft)
                .build();

        navigationDrawerLeft.setSelection(home);
    }

}
